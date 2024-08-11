package com.suitmedia.test1.ui.secondscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suitmedia.test1.data.preferences.UserPreference

class SecondViewModel(
    private val userPreferences : UserPreference
): ViewModel() {
    fun getUsername() : LiveData<String> {
        return userPreferences.getUsername().asLiveData()
    }
}