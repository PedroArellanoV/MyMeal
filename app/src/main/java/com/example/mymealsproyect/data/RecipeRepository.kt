package com.example.mymeal.data

import com.example.mymeal.data.remote.SpoonacularService
import com.example.mymeal.domain.model.RecipeInformation
import com.example.mymeal.domain.model.SearchResult
import com.example.mymeal.domain.model.toDomain
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val retrofit: SpoonacularService) {

    suspend fun searchRecipe(query: String): SearchResult? {
        val response = retrofit.searchRecipe(query)
        return response?.toDomain()
    }

    suspend fun getRandomRecipe(): RecipeInformation? {
        val response = retrofit.getRandomRecipe()
        return response?.recipes?.get(0)?.toDomain()
    }
}