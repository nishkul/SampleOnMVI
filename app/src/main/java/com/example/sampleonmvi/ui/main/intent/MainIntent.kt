package com.example.sampleonmvi.ui.main.intent

sealed class MainIntent{
    object GetPosts : MainIntent()
}