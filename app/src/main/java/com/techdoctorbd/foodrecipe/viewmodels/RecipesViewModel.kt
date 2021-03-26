package com.techdoctorbd.foodrecipe.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.techdoctorbd.foodrecipe.utils.Constants
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_API_KEY
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_DIET
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_FILL_INGREDIENTS
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_NUMBER
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_TYPES

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPES] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}