package com.example.mymeal.domain.model

import com.example.mymeal.data.remote.model.RequestResults


data class RecipeResult(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String
)

fun RequestResults.toDomain() = RecipeResult(id, title, image, imageType)
