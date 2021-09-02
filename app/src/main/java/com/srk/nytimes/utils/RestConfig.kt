package com.srk.nytimes.utils

/**
 * Created by Sujith RK on 30,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
class RestConfig {
    companion object {

//      API Header key and value
        const val CONTENT_TYPE_KEY = "Content-Type"
        const val CONTENT_TYPE_VALUE = "application/json"

//      Client TimeOut
        const val READ_TIME_OUT: Long = 60
        const val CONNECTION_TIME_OUT: Long = 60

//       API Version
        private const val API_VERSION = "v2"

//      REST API Path key
        const val SECTION_KEY = "section"
        const val PERIOD_KEY = "period"
        const val JSON_KEY = ".json"
        const val API_KEY = "api-key"


        /*Relative Url
        GET*/
        const val GET_MOST_POPULAR = "svc/mostpopular/$API_VERSION/mostviewed/"
    }
}