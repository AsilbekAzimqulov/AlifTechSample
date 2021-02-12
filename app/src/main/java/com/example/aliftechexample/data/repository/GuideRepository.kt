package com.example.aliftechexample.data.repository

import android.util.Log
import com.example.aliftechexample.data.core.LocalDatabase
import com.example.aliftechexample.data.local.GuideDatabase
import com.example.aliftechexample.data.local.entity.Guide
import com.example.aliftechexample.data.remote.response.GuideData
import com.example.aliftechexample.data.remote.response.GuideResponse
import com.example.aliftechexample.data.remote.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.Response

interface GuideRepository {
    suspend fun getGuide(): Response<GuideResponse>
    suspend fun getGuidesLocal(): Flow<List<Guide>>
}

class GuideRepositoryImpl(
    private val httpService: ApiService = ApiService.get(),
    private val database: GuideDatabase = LocalDatabase.getInstanceDatabase()
) : GuideRepository {

    override suspend fun getGuide(): Response<GuideResponse> {
        return httpService.getGuideList()
    }

    override suspend fun getGuidesLocal() = flow<List<Guide>> {
        emit(database.getGuideDao().getGuide())
        try {
            val data = httpService.getGuideList()
            if (data.isSuccessful && data.body() != null) {
                database.getGuideDao().update(wrapData(data.body()!!.data))
            }
        } catch (ex: Exception) {
Log.d("MRX",ex.message.toString())
        }
    }

    private fun wrapData(list: List<GuideData>): List<Guide> {
        return list.map {
            Guide(
                it.url,
                it.startDate,
                it.endDate,
                it.name,
                it.icon,
                it.objType,
                it.loginRequired
            )
        }
    }
}