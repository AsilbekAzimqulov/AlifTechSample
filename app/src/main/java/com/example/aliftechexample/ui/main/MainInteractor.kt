package com.example.aliftechexample.ui.main

import com.example.aliftechexample.data.local.entity.Guide
import com.example.aliftechexample.data.repository.GuideRepository
import com.example.aliftechexample.data.repository.GuideRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

interface MainInteractor {
    suspend fun getGuide(): Flow<List<Guide>>
    suspend fun loadMoreGuide():Flow<List<Guide>>
}

class MainInteractorImpl : MainInteractor {

    private val repository: GuideRepository by lazy { GuideRepositoryImpl() }

    override suspend fun getGuide(): Flow<List<Guide>> {
        return flow {
        repository.getGuidesLocal().collect {
            emit(it.subList(0,2))
        }
        }
    }

    override suspend fun loadMoreGuide(): Flow<List<Guide>> {
        return repository.getGuidesLocal()
    }


}