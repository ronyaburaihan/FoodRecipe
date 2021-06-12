package com.techdoctorbd.foodrecipe.data

import com.techdoctorbd.foodrecipe.data.models.FoodRecipe
import com.techdoctorbd.foodrecipe.data.network.FoodRecipeApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipeApi: FoodRecipeApi
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipeApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQueries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipeApi.searchRecipes(searchQueries)
    }
}