package com.example.sampleonmvi.data.respository

import com.example.sampleonmvi.data.api.ApiService

class UserRepository(val apiService: ApiService) {

    suspend fun getPosts() = apiService.getPost()
}