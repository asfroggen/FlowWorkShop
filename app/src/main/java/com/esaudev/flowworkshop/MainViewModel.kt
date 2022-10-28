package com.esaudev.flowworkshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _stateFlow = MutableStateFlow<UiStates>(UiStates.StandBy)
    val stateFlow: StateFlow<UiStates>
        get() = _stateFlow

    private val _livedata = MutableLiveData<UiStates>(UiStates.StandBy)
    val livedata: LiveData<UiStates>
        get() = _livedata

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    val flows = flowOf(1,2,3)

    init {
        viewModelScope.launch {
            repeat(100) {
                _sharedFlow.emit(it)
                delay(1000L)
            }
        }
    }

    fun sendStateFlow() {
        viewModelScope.launch {
            _stateFlow.value = UiStates.NetworkError
            _livedata.value = UiStates.NetworkError
        }
    }
}