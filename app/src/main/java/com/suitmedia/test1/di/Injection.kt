package com.suitmedia.test1.di

import android.content.Context
import com.suitmedia.test1.api.RetrofitClient
import com.suitmedia.test1.data.preferences.UserPreference
import com.suitmedia.test1.data.preferences.dataStoreUser
import com.suitmedia.test1.data.repository.UserRepository
import com.suitmedia.test1.database.UsersRoomDatabase

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UsersRoomDatabase.getDatabase(context)
        val apiService = RetrofitClient.instanceApiRegres
        return UserRepository(database, apiService)
    }

    fun provideUserPreference(context: Context) : UserPreference {
        return UserPreference.getInstance(context.dataStoreUser)
    }
}