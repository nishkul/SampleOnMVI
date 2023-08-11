package com.example.sampleonmvi.ui.main.viewstate


sealed class MainVIewSate {
    object Idle : MainVIewSate()
    object Loading : MainVIewSate()
    data class Success<T>(val data: T) : MainVIewSate()
    data class Error(val message: String) : MainVIewSate()
}

