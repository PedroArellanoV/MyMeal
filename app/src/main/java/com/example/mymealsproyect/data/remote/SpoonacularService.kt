package com.example.mymeal.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mymeal.data.remote.model.RequestRecipeInformation
import com.example.mymeal.data.remote.model.RequestSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class SpoonacularService @Inject constructor(private val retrofit: SpoonacularApiService) {

    private val apiKey = String(Base64.getDecoder().decode("NmY2NmVmMjc2Y2Y4NDhjMjhiNWUxZjZmYmNhNDA2ZWU="))

    suspend fun searchRecipe(query: String): RequestSearchResult? {
        return withContext(Dispatchers.IO){
            val response = retrofit.searchRecipe(apiKey, query)
            response.body()
        }
    }

    suspend fun getRandomRecipe(): RequestRecipeInformation? {
        return withContext(Dispatchers.IO){
            val response = retrofit.getRandomRecipe(apiKey)
            response.body()
        }
    }
}