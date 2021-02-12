package com.example.aliftechexample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aliftechexample.data.local.entity.Guide
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val interactor: MainInteractor by lazy { MainInteractorImpl() }

    val guide: MutableLiveData<List<Guide>> = MutableLiveData()

    fun getGuide() {
        viewModelScope.launch {
            interactor.getGuide()
                .catch { ex ->
//error
                }
                .collect { data ->
                    guide.postValue(data)
                }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            interactor.loadMoreGuide()
                .catch { ex ->

                }
                .collect {
                    guide.postValue(it)
                }
        }
    }
}