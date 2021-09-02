package com.srk.nytimes.data.remote.model.param

/**
 * Created by Sujith RK on 30,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
data class MostPopularParam(
    val section: String,
    val period: Int,
    val apiKey: String
)
