package com.srk.nytimes.data.remote

import com.srk.nytimes.utils.Resource
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }

            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.e("remoteDataSource %s", message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}