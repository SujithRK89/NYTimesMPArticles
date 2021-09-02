package com.srk.nytimes.data.remote

import com.srk.nytimes.data.remote.model.param.MostPopularParam
import javax.inject.Inject

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
class RemoteDataSource @Inject constructor(
    private val apiService: APIService
) : BaseDataSource() {

    //  GET
    suspend fun getMostPopular(param: MostPopularParam) =
        getResult { apiService.getMostPopular(param.section, param.period, param.apiKey) }

}