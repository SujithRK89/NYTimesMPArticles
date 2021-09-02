package com.srk.nytimes.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Sujith RK on 30,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
@HiltAndroidApp
class NYTApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Timber for logging
        Timber.plant(Timber.DebugTree())
    }
}