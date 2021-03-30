package com.techdoctorbd.foodrecipe.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.techdoctorbd.foodrecipe.data.database.RecipesEntity
import com.techdoctorbd.foodrecipe.models.FoodRecipe
import com.techdoctorbd.foodrecipe.utils.NetworkResult

class RecipesBinding {

    companion object {

        @BindingAdapter("apiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(
            view: View,
            apiResponse: NetworkResult<FoodRecipe>?,
            readDatabase: List<RecipesEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && readDatabase.isNullOrEmpty()) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }
    }
}