package com.srk.nytimes.utils

import androidx.test.platform.app.InstrumentationRegistry
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Sujith RK on 01,September,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
object FileReader {
    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}