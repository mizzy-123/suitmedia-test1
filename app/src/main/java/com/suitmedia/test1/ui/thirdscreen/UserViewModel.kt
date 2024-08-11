package com.suitmedia.test1.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.test1.data.model.response.ListUsers
import com.suitmedia.test1.data.preferences.UserPreference
import com.suitmedia.test1.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    userRepository: UserRepository,
    private val userPreferences : UserPreference
) : ViewModel() {
    val story: LiveData<PagingData<ListUsers>> = userRepository.getStory().cachedIn(viewModelScope)

    fun setUsername(username: String){
        viewModelScope.launch {
            userPreferences.setUsername(username)
        }
    }

    fun getUsername() : LiveData<String> {
        return userPreferences.getUsername().asLiveData()
    }
}