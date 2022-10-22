package com.hyosik.android.uistatesample

import androidx.lifecycle.ViewModel
import com.hyosik.android.uistatesample.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel() : ViewModel() {

     private val _uiState : StateFlow<UiState> = MutableStateFlow(UiState.UnInitialized)

}