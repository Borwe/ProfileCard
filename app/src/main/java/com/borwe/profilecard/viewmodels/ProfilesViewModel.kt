package com.borwe.profilecard.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borwe.profilecard.data.ProfilesRepository
import com.borwe.profilecard.model.MemberProfile

class ProfilesViewModel(private val profilesRepo: ProfilesRepository): ViewModel() {
    val profiles = profilesRepo.profiles
    val emailDrawable = profilesRepo.emailDrawable
}

class ProfilesViewModelFactory(val ctx: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfilesViewModel::class.java)){
            return ProfilesViewModel(ProfilesRepository(ctx = ctx)) as T
        }
        throw IllegalArgumentException("modelClass not assignable to ProfilesViewModel::class")
    }
}
