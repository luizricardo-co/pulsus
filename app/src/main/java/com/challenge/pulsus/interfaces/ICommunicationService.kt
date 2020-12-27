package com.challenge.pulsus.interfaces

import com.challenge.pulsus.models.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ICommunicationService {
    @GET("categories/")
    fun getCategories(): Call<List<String>>

    @GET("random")
    fun category(@Query("category") category: String): Call<Data>
}