package com.challenge.pulsus.interfaces

interface ICategoryResponse<T> {
    fun success(response: T)
    fun failure(error: String)
}