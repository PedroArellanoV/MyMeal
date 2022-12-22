package com.example.mymeal.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.domain.GetRandomRecipeUseCase
import com.example.mymeal.domain.model.RecipeInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getRandomRecipeUseCase: GetRandomRecipeUseCase
): ViewModel() {

    val todayRecommendation = MutableLiveData<RecipeInformation>()

    fun getRandomRecipe(){
        viewModelScope.launch {
            val result = getRandomRecipeUseCase()
            if (result != null){
                todayRecommendation.postValue(result!!)
            }
        }
    }


}