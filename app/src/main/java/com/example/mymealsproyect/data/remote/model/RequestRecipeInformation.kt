package com.example.mymeal.data.remote.model

import com.google.gson.annotations.SerializedName

data class RequestRecipeInformation(
    @SerializedName("recipes") val recipes: List<RequestInformation>
)
