package com.example.mymeal.di

import com.example.mymeal.data.remote.SpoonacularApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val RECIPES_BASE_URL = "https://api.spoonacular.com/recipes/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RECIPES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun spoonacularApiClient(retrofit: Retrofit): SpoonacularApiService{
        return retrofit.create(SpoonacularApiService::class.java)
    }

}