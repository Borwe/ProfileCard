package com.borwe.profilecard.data

import android.content.Context
import android.graphics.drawable.Drawable
import com.borwe.profilecard.R
import com.borwe.profilecard.model.MemberProfile

class ProfilesRepository(ctx: Context) {

    val profiles: MutableList<MemberProfile> = mutableListOf(
        MemberProfile(name="Brian", pic = ctx.getDrawable(R.drawable.mine)!!, email = "brian.orwe@gmail.com", bio =  """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
    Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
""".trimIndent()),
        MemberProfile(
            name="Samuel", pic = ctx.getDrawable(R.drawable.samuel)!!, bio =  """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
        Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
    """.trimIndent(), email = "placeholder@gmail.com"
        ),
        MemberProfile(
            name="Alvine", pic = ctx.getDrawable(R.drawable.alvine)!!, bio =  """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
        Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
    """.trimIndent(), email = "placeholder@gmail.com"
        ),
        MemberProfile(
            name="Nura", pic = ctx.getDrawable(R.drawable.resource_default)!!, bio =  """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
        Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
    """.trimIndent(), email = "placeholder@gmail.com"
        ),
        MemberProfile(
            name="Allan", pic = ctx.getDrawable(R.drawable.resource_default)!!, bio =  """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
        Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
    """.trimIndent(), email = "placeholder@gmail.com"
        ),
    )

    val groupName = "Hutsy 5"

    val emailDrawable: Drawable = ctx.getDrawable(android.R.drawable.ic_dialog_email)!!
}
