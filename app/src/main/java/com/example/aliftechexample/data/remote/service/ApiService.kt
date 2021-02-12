package com.example.aliftechexample.data.remote.service

import com.example.aliftechexample.data.core.ApiRetrofit
import com.example.aliftechexample.data.remote.response.GuideResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        fun get() = ApiRetrofit.createService(ApiService::class.java)
    }

    @GET("service/v2/upcomingGuides/")
    suspend fun getGuideList(): Response<GuideResponse>
}