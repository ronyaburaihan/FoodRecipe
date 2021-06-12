package com.techdoctorbd.foodrecipe.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.techdoctorbd.foodrecipe.data.DataStoreRepository
import com.techdoctorbd.foodrecipe.utils.Constants.API_KEY
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_DIET_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_MEAL_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_RECIPES_NUMBER
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_API_KEY
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_DIET
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_FILL_INGREDIENTS
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_NUMBER
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_SEARCH
import com.techdoctorbd.foodrecipe.utils.Constants.QUERY_TYPES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesViewModel @ViewModelInject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    private fun saveBackOnline(backOnline: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }
    }

    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun applyQueries(): HashMap<String, String> {

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPES] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else {
            if (backOnline) {
                Toast.makeText(getApplication(), "You're back online", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
}