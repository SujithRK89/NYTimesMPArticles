package com.srk.nytimes.data.remote.model


import com.google.gson.annotations.SerializedName

data class MostPopularResponse(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: List<MostPopular>?,
    @SerializedName("status")
    val status: String?
)