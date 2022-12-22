package com.example.mymeal.data.remote

import com.example.mymeal.data.remote.model.RequestRecipeInformation
import com.example.mymeal.data.remote.model.RequestSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SpoonacularApiService {

    @Headers("Content-Type: application/json")
    @GET("complexSearch")
    suspend fun searchRecipe(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String
    ): Response<RequestSearchResult>

    @Headers("Content-Type: application/json")
    @GET("random")
    suspend fun getRandomRecipe(
        @Query("apiKey") apiKey: String): Response<RequestRecipeInformation>
}