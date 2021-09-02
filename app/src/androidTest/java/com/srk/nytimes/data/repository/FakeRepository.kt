package com.srk.nytimes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.srk.nytimes.data.remote.model.MostPopularResponse
import com.srk.nytimes.data.remote.model.param.MostPopularParam
import com.srk.nytimes.utils.FileReader
import com.srk.nytimes.utils.Resource
import com.google.gson.Gson

/**
 * Created by Sujith RK on 01,September,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
class FakeRepository : BaseRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private val testValue: MutableLiveData<Resource<MostPopularResponse>> = MutableLiveData()


    override suspend fun getMostPopular(param: MostPopularParam): LiveData<Resource<MostPopularResponse>> {
        return if (shouldReturnNetworkError) {
            testValue.postValue(Resource.error("Api Returns Error", null))
            testValue
        }
        else {
            testValue.postValue(Resource.success(getResponse()))
            testValue
        }
    }

    private fun getResponse(): MostPopularResponse {
        val jsonString = FileReader.readStringFromFile("success_response.json")
        return Gson().fromJson(jsonString, MostPopularResponse::class.java)
    }
}