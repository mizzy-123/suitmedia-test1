package com.suitmedia.test1.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.*
import androidx.paging.PagingData
import com.suitmedia.test1.api.ApiService
import com.suitmedia.test1.data.model.response.ListUsers
import com.suitmedia.test1.data.paging.UserRemoteMediator
import com.suitmedia.test1.database.UsersRoomDatabase

class UserRepository(
    private val usersRoomDatabase: UsersRoomDatabase,
    private val apiService: ApiService
) {
    fun getStory(): LiveData<PagingData<ListUsers>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = UserRemoteMediator(usersRoomDatabase, apiService),
            pagingSourceFactory = {
                usersRoomDatabase.userDao().getAllUser()
            }
        ).liveData
    }
}