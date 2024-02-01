package com.borwe.profilecard.data

import android.util.Log
import com.borwe.profilecard.model.MemberProfile
import com.borwe.profilecard.viewmodels.Direction

class ProfilesRepository {
    fun getCurrent(): MemberProfile {
        return profile[index]
    }

    suspend fun updateCurrent(direction: Direction) {
        if(direction==Direction.SCROLL_UP){
            index--
            if(index <0 ){
                index = profile.size-1
            }
        }else if(direction==Direction.SCROLL_DOWN){
            index++
            if(index >= profile.size){
                index = 0
            }
        }
        Log.d("DIREDBABY","${index}")
    }

    private var index: Int = 0

    val profile: MutableList<MemberProfile> = mutableListOf(
        MemberProfile(name="Brian"),
        MemberProfile(name="Samuel"),
        MemberProfile(name="Allan"),
        MemberProfile(name="Nura"),
    )

    val groupName = "Hutsy 5"
}
