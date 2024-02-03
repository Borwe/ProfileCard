package com.borwe.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borwe.profilecard.ui.theme.ProfileCardTheme
import com.borwe.profilecard.ui.theme.Typography
import com.borwe.profilecard.viewmodels.ProfilesViewModel
import com.borwe.profilecard.viewmodels.ProfilesViewModelFactory
import com.borwe.profilecard.views.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profilesViewModel: ProfilesViewModel by viewModels{ ProfilesViewModelFactory(this) }

        setContent {
            ProfileCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(50,155,50)
                ) {
                    Main( profilesViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(profilesViewModel: ProfilesViewModel) {
    Scaffold(topBar =
    {
        TopAppBar( title = {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center){
                Text(text = "HUTSY 5 MEMBERS", style = Typography.titleLarge)
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(50,155,50)
        ))
    }, containerColor = Color(50,155,50)
    ) {
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var profileUsers: Long = 0
            for(member in profilesViewModel.profiles){
                profileUsers+=1
                val profileState  = remember { MutableTransitionState(false).apply {
                    CoroutineScope(Dispatchers.IO).launch{
                        delay(profileUsers * 1000)
                        targetState = true
                    }
                }}
                AnimatedVisibility(visibleState = profileState,
                    enter = slideInVertically(initialOffsetY = {
                        -300
                    })
                ) {
                    Profile(member, profilesViewModel.emailDrawable)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileCardTheme {
    }
}