package com.challenge.pulsus.models

import com.challenge.pulsus.interfaces.ICommunicationService
import com.challenge.pulsus.utils.URL_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkInitializer {

    private val retrofit  = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun communicationService() = retrofit.create(ICommunicationService::class.java)

}