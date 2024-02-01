package com.borwe.profilecard.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

enum class Direction {
    SCROLL_UP, SCROLL_DOWN, NONE
}

class SwipeViewModel(): ViewModel() {
    private val _swiped = mutableStateOf(Direction.NONE)
    val swiped: State<Direction> = _swiped
    private val _finalSwipe = MutableLiveData<Direction>()
    val finalSwapFlow: LiveData<Direction> = _finalSwipe

    fun onSwiping(swipingDirection: Direction) = viewModelScope.launch {
        _swiped.value = swipingDirection
    }

    fun onDoneSwiping() = viewModelScope.launch {
        _finalSwipe.value = swiped.value
        if(swiped.value == Direction.SCROLL_DOWN){
            Log.d("SWIPPED_INFO:","DOWn")
        }else if(swiped.value == Direction.SCROLL_UP){
            Log.d("SWIPPED_INFO:","UP")
        }else{
            Log.d("SWIPPED_INFO:","NONE")
        }
    }
}

