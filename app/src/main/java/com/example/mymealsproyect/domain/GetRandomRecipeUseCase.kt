package com.example.mymeal.domain

import com.example.mymeal.data.RecipeRepository
import com.example.mymeal.domain.model.RecipeInformation
import javax.inject.Inject

class GetRandomRecipeUseCase @Inject constructor(private val repository: RecipeRepository){
    suspend operator fun invoke(): RecipeInformation? = repository.getRandomRecipe()
}