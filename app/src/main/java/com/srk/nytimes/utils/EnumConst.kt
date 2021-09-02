package com.srk.nytimes.utils

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */

enum class Section(val value: String) {
    ALL_SECTIONS("all-sections")
}

enum class Period(val value: Int) {
    DAY(1),
    WEEK(7),
    MONTH(30)
}

enum class ImageType() {
    THUMB,
    FULL
}