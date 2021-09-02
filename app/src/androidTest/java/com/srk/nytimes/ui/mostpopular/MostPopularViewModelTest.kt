package com.srk.nytimes.ui.mostpopular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.srk.nytimes.MainCoroutineRule
import com.srk.nytimes.data.repository.FakeRepository
import com.srk.nytimes.getOrAwaitValue
import com.srk.nytimes.utils.NetworkHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Created by Sujith RK on 02,September,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
class MostPopularViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Inject
    lateinit var networkHandler: NetworkHelper

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var fakeRepository: FakeRepository

    private lateinit var viewModel: MostPopularViewModel

    @Before
    fun init() {
        hiltRule.inject()
        fakeRepository = FakeRepository()
        viewModel = MostPopularViewModel(fakeRepository, networkHandler)
    }

    @Test
    fun checkIsNetworkAvailable() {
        assertThat(networkHandler.isNetworkConnected()).isTrue()
    }

    @Test
    fun mostPopularLiveDataHasValueAfterSuccessFromAPI()  = runBlocking {
        viewModel.invokeMostPopular()
        assertThat(viewModel.mostPopularList.getOrAwaitValue {  }.isEmpty()).isFalse()
        assertThat(viewModel.message.value == null).isTrue()
    }

    @Test
    fun liveDataHasValueAfterNetworkCall()  = runBlocking {
        viewModel.invokeMostPopular()
        assertThat(viewModel.mostPopularList.getOrAwaitValue {  }.first().id == 100000007938314).isTrue()
        assertThat(viewModel.message.value == null).isTrue()
    }

    @Test
    fun loaderDismissAfterNetworkCall() = runBlocking {
        viewModel.invokeMostPopular()
        assertThat(viewModel.isLoading.getOrAwaitValue {  }).isFalse()
        assertThat(viewModel.message.value == null).isTrue()
    }

    @Test
    fun mostPopularLiveDataNullHasErrorMsgAfterErrorFromAPI()  = runBlocking {
        val fakeRepository = FakeRepository()
        fakeRepository.setShouldReturnNetworkError(true)
        val viewModel = MostPopularViewModel(fakeRepository, networkHandler)
        assertThat(viewModel.mostPopularList.value == null).isTrue()
        assertThat(viewModel.message.getOrAwaitValue {  }.getContentIfNotHandled() == "Api Returns Error").isTrue()
    }

}