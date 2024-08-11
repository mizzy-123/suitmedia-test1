package com.suitmedia.test1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suitmedia.test1.data.model.response.ListUsers

@Database(
    entities = [ListUsers::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class UsersRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: UsersRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UsersRoomDatabase {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UsersRoomDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}