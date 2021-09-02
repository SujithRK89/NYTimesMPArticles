package com.srk.nytimes.data.remote

import com.srk.nytimes.data.remote.model.MostPopularResponse
import com.srk.nytimes.utils.RestConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Sujith RK on 30,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
interface APIService {

    //  GET
    @GET("${RestConfig.GET_MOST_POPULAR}{${RestConfig.SECTION_KEY}}/{${RestConfig.PERIOD_KEY}}${RestConfig.JSON_KEY}")
    suspend fun getMostPopular(
        @Path(RestConfig.SECTION_KEY) section: String,
        @Path(RestConfig.PERIOD_KEY) period: Int,
        @Query(RestConfig.API_KEY) apiKey: String
    ): Response<MostPopularResponse>
}