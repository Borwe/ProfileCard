package com.borwe.profilecard.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borwe.profilecard.data.ProfilesRepository
import com.borwe.profilecard.model.MemberProfile

class ProfilesViewModel(val profilesRepo: ProfilesRepository): ViewModel() {

    private val _currentProfile: MutableState<MemberProfile> = mutableStateOf(profilesRepo.getCurrent())
    val currentProfile: State<MemberProfile> = _currentProfile

    suspend fun onChangeProfile(direction: Direction){
        profilesRepo.updateCurrent(direction)
        _currentProfile.value = profilesRepo.getCurrent()
    }
}

class ProfilesViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfilesViewModel::class.java)){
            return ProfilesViewModel(ProfilesRepository()) as T
        }
        throw IllegalArgumentException("modelClass not assignable to ProfilesViewModel::class")
    }
}
