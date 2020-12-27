package com.challenge.pulsus.controllers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challenge.pulsus.models.Data

class MainViewModel(private val repository: NetworkWebClient) : ViewModel() {
    val categoryLiveData = MutableLiveData<List<String>>()
    val handlerError = MutableLiveData<Throwable>()
    val dataDetail  = MutableLiveData<Data>()

    fun getCategories(){
        repository.list(
            {
                categoryLiveData.postValue(it)
            },{
                throwable ->  handlerError.postValue(throwable)
            })
    }

    fun getCategoryDetail (category: String) {
        repository.categoryDetail(category, {
            dataDetail.value = it
        },{
            throwable ->  handlerError.value =throwable
        })
    }

    class MainViewModelFactory(private val repository: NetworkWebClient):
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}