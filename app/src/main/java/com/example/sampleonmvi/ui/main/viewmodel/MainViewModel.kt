package com.example.sampleonmvi.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleonmvi.data.respository.UserRepository
import com.example.sampleonmvi.ui.main.intent.MainIntent
import com.example.sampleonmvi.ui.main.viewstate.MainVIewSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val mainIntent: Channel<MainIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainVIewSate>(MainVIewSate.Idle)
    val state: StateFlow<MainVIewSate>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.GetPosts -> fetchPosts()
                }
            }
        }

    }

    private suspend fun fetchPosts() {
        viewModelScope.launch {
            _state.value = MainVIewSate.Loading
            try {
                _state.value = MainVIewSate.Success(data = userRepository.getPosts())
            } catch (e: Exception) {
                _state.value = MainVIewSate.Error(message = e.message.toString())
            }
        }
    }
}

