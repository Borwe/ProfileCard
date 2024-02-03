package com.borwe.profilecard.model

import android.graphics.drawable.Drawable

data class MemberProfile(val name: String, val pic: Drawable, val bio: String, val email: String){
    override fun hashCode(): Int {
        return name.hashCode() + bio.hashCode()
    }
}
