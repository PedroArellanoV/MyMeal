package com.example.mymeal.data.remote.model

import com.google.gson.annotations.SerializedName

data class RequestResults(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("imageType") val imageType: String
)
