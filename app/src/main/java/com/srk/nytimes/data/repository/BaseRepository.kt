package com.srk.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.srk.nytimes.data.remote.model.MostPopularResponse
import com.srk.nytimes.data.remote.model.param.MostPopularParam
import com.srk.nytimes.utils.Resource

/**
 * Created by Sujith RK on 01,September,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
interface BaseRepository {
    suspend fun getMostPopular(param: MostPopularParam): LiveData<Resource<MostPopularResponse>>
}