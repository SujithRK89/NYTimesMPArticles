package com.srk.nytimes.handler

import com.srk.nytimes.data.remote.model.MostPopular

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
interface MPItemHandler {
    fun onItemClick(item: MostPopular)
}