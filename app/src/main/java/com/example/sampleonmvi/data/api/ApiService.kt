package com.example.sampleonmvi.data.api

import com.example.sampleonmvi.data.model.FakeUserDataDTO
import retrofit2.http.GET

interface ApiService  {

    @GET("posts")
    suspend fun getPost():List<FakeUserDataDTO>
}