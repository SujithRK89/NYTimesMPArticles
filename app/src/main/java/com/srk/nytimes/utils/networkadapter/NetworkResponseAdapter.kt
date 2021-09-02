package com.srk.nytimes.utils.networkadapter

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */

class NetworkResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(call, errorBodyConverter)
    }
}
