package com.suitmedia.test1.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suitmedia.test1.data.model.response.ListUsers

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<ListUsers>)

    @Query("SELECT * FROM users")
    fun getAllUser(): PagingSource<Int, ListUsers>

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}