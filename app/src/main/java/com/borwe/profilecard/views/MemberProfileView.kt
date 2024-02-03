package com.borwe.profilecard.views

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.borwe.profilecard.R
import com.borwe.profilecard.model.MemberProfile
import com.borwe.profilecard.ui.theme.ProfileCardTheme
import com.borwe.profilecard.ui.theme.Typography

@Composable
fun Profile(member: MemberProfile, email: Drawable){
    val width = LocalConfiguration.current.screenWidthDp * 0.95
    var expanded by remember {
        mutableStateOf(false)
    }

    ElevatedCard(modifier = Modifier
        .width(width.dp)
        .animateContentSize()
        .padding(10.dp),
        elevation = CardDefaults.cardElevation(3.dp)
        ) {
        Row(modifier = Modifier .fillMaxWidth(),
            Arrangement.Center) {

                if(expanded){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                        Image(bitmap = member.pic.toBitmap().asImageBitmap(),
                            contentDescription = "${member.name}'s Profile pic",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .clip(RoundedCornerShape(50))
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(50)
                                )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = member.name, style = Typography.labelLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton( onClick = { expanded = !expanded  }) {
                            Icon(painter = LocalContext.current.run {
                                painterResource(id = android.R.drawable.ic_menu_close_clear_cancel)
                            }, contentDescription = "Close expansion")
                        }

                    }
                }else{
                    Image(bitmap = member.pic.toBitmap().asImageBitmap(),
                        contentDescription = "${member.name}'s Profile pic",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(50))
                            .border(
                                width = 5.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(50)
                            )
                    )
                }
        }
        if(!expanded){
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(text = member.name, style = Typography.labelLarge)
            }
        }
        Column(modifier = Modifier.padding(horizontal = 15.dp)) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10))
               ){
                if(expanded){
                    Text(text = member.bio, style = Typography.bodyMedium, modifier = Modifier.padding(10.dp)  )
                }else{
                    Text(text = member.bio, maxLines = 2, style = Typography.bodyMedium, modifier = Modifier.padding(10.dp)  )
                }
            }
            Row(modifier = Modifier.padding(10.dp)){
                Icon(bitmap = email.toBitmap().asImageBitmap(), contentDescription = "Email icon")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = member.email)
            }
        }
        Row (modifier = Modifier.fillMaxWidth(), Arrangement.Center){
            AnimatedVisibility(visible = !expanded) {
                ElevatedButton(onClick = { expanded = !expanded  }, modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(text = "View Profile")
                }
            }
        }
    }
}

@Composable
@Preview
fun MemberProfilePreview(){
    val ctx = LocalContext.current
    var member by remember {
        mutableStateOf(MemberProfile(
            name="Brian", pic = BitmapDrawable(ctx.resources,
                BitmapFactory.decodeResource(ctx.resources, R.drawable.mine)), "HEHEHEH YOLO", "brian.orwe@gmail.com"
        ))
    }
    ProfileCardTheme {
        Profile(member, ctx.getDrawable(android.R.drawable.ic_dialog_email)!!)
    }
}