package com.srk.nytimes.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */

fun <T> performGetOperation(networkCall: suspend () -> Resource<T>):
        LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            val data: MutableLiveData<T> = MutableLiveData<T>()
            data.postValue(responseStatus.data!!)
            val source = data.map { Resource.success(it) }
            emitSource(source)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }