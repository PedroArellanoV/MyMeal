package com.example.mymeal.data.remote.model

import com.google.gson.annotations.SerializedName

data class RequestSearchResult(
    @SerializedName("results") val results: List<RequestResults>,
    @SerializedName("offset") val offset: Int,
    @SerializedName("number") val number: Int,
    @SerializedName("totalResults") val totalResults: Int
)
