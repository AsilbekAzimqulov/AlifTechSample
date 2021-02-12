package com.example.aliftechexample.data.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    private const val wallHavenAddress = "https://guidebook.com/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(wallHavenAddress)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}