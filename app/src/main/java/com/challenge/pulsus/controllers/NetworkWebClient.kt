package com.challenge.pulsus.controllers

import android.util.Log
import com.challenge.pulsus.models.Data
import com.challenge.pulsus.models.NetworkInitializer

class NetworkWebClient {

    fun list(success: (categories: List<String>) -> Unit , failure: (throwable: Throwable) -> Unit){
        val call  = NetworkInitializer().communicationService().getCategories()

        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.message?.let {
                Log.e("onFailure error", it)
            }
            if (throwable != null) {
                failure(throwable)
            }
        }))
    }

    fun categoryDetail(detail: String, success: (categories: Data) -> Unit
                       , failure: (throwable: Throwable) -> Unit){
        val call  = NetworkInitializer().communicationService().category(detail)
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.message?.let {
                Log.e("onFailure error", it)
            }
            if (throwable != null) {
                failure(throwable)
            }
        }))
    }
}