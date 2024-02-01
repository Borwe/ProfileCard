package com.borwe.profilecard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.borwe.profilecard.ui.theme.ProfileCardTheme
import com.borwe.profilecard.viewmodels.Direction
import com.borwe.profilecard.viewmodels.ProfilesViewModel
import com.borwe.profilecard.viewmodels.ProfilesViewModelFactory
import com.borwe.profilecard.viewmodels.SwipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val swipingViewModel: SwipeViewModel by viewModels()
        val profilesViewModel: ProfilesViewModel by viewModels{ ProfilesViewModelFactory() }

        swipingViewModel.finalSwapFlow.observe(this, {
            profilesViewModel.viewModelScope.launch {
                profilesViewModel.onChangeProfile(it)
            }
        })
        setContent {
            ProfileCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main( swipingViewModel, profilesViewModel)
                }
            }
        }
    }
}

@Composable
fun Main(swipeViewModel: SwipeViewModel, profilesViewModel: ProfilesViewModel, modifier: Modifier = Modifier) {
    Scaffold() {
        Column(modifier = Modifier.padding(it)
            .fillMaxSize()
            .pointerInput(Unit){
                detectVerticalDragGestures(onDragEnd = {
                    swipeViewModel.onDoneSwiping()
                }) { change, dragAmount ->
                    Log.d("AMOUNT:",dragAmount.toString())
                    if(dragAmount<0){
                        swipeViewModel.onSwiping(Direction.SCROLL_UP)
                    }else if(dragAmount > 0){
                        swipeViewModel.onSwiping(Direction.SCROLL_DOWN)
                    }
                }
            }
        ) {
            Text(
                text = "Hello ${profilesViewModel.currentProfile.value.name}",
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileCardTheme {
    }
}