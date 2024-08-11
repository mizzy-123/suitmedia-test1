package com.suitmedia.test1.ui.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suitmedia.test1.data.preferences.UserPreference
import com.suitmedia.test1.data.repository.UserRepository
import com.suitmedia.test1.di.Injection
import com.suitmedia.test1.ui.secondscreen.SecondViewModel
import com.suitmedia.test1.ui.thirdscreen.UserViewModel

class ViewModelFactory private constructor(
    private val application: Application,
    private val userPreferences : UserPreference
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){
            UserViewModel::class.java -> UserViewModel(Injection.provideRepository(application), userPreferences) as T
            SecondViewModel::class.java -> SecondViewModel(userPreferences) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(
                        application,
                        Injection.provideUserPreference(application)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}