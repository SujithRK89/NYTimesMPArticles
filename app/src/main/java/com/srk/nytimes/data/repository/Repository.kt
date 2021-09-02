package com.srk.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.srk.nytimes.data.remote.RemoteDataSource
import com.srk.nytimes.data.remote.model.MostPopularResponse
import com.srk.nytimes.data.remote.model.param.MostPopularParam
import com.srk.nytimes.utils.Resource
import com.srk.nytimes.utils.performGetOperation
import javax.inject.Inject

/**
 * Created by Sujith RK on 30,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseRepository {

    override suspend fun getMostPopular(param: MostPopularParam): LiveData<Resource<MostPopularResponse>> {
        return performGetOperation(networkCall = { remoteDataSource.getMostPopular(param) })
    }

}