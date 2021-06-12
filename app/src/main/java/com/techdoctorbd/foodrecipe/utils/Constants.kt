package com.techdoctorbd.foodrecipe.utils

object Constants {

    const val BASE_URL = "https://api.spoonacular.com"
    const val API_KEY = "fff04fafe0844fc0ba5e6890eb315512"

    //API Query Keys
    const val QUERY_SEARCH = "query"
    const val QUERY_NUMBER = "numbers"
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_TYPES = "types"
    const val QUERY_DIET = "diet"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"

    //For ROOM Database
    const val DATABASE_NAME = "recipes_database"
    const val RECIPES_TABLE = "recipes_table"

    //Bottom sheet and preferences
    const val DEFAULT_RECIPES_NUMBER = "50"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"

    const val PREFERENCE_NAME = "recipePreference"
    const val PREFERENCE_MEAL_TYPE = "mealType"
    const val PREFERENCE_MEAL_TYPE_ID = "mealTypeId"
    const val PREFERENCE_DIET_TYPE = "dietType"
    const val PREFERENCE_DIET_TYPE_ID = "dietTypeId"
    const val PREFERENCE_BACK_ONLINE = "backOnline"

}