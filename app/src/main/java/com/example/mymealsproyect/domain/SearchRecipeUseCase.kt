package com.example.mymeal.domain

import com.example.mymeal.data.RecipeRepository
import com.example.mymeal.domain.model.SearchResult
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(query: String): SearchResult? = repository.searchRecipe(query)
}