package com.challenge.pulsus.controllers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    @get:Rule
    private val rule = InstantTaskExecutorRule()

    private val repository = mockk<NetworkWebClient>()
    private val onCategoryList = mockk<Observer<List<String>>>(relaxed = true)
    private var mockedList: List<String> = listOf("animal", "cinema", "sport")

    private fun instanciateViewModel (): MainViewModel {
        val viewModel = MainViewModel(repository)
//        viewModel.categoryLiveData.observeForever(onCategoryList)

        return viewModel
    }

    @Test
    fun `when view model fetches data then should call repository`() {
        val viewModel = instanciateViewModel()

        every {repository.list({},{})} returns list({},{})

        viewModel.getCategories()

        verify { repository.list({
            mockedList = it
        },{}) }
//        verify { onCategoryList.onChanged(mockedList) }
    }

    private fun list(success: (categories: List<String>) -> Unit, failure: (throwable: Throwable) -> Unit){
                success(listOf("animal", "sport"))
    }
}
