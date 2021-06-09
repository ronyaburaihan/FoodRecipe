package com.techdoctorbd.foodrecipe.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.techdoctorbd.foodrecipe.data.models.FoodRecipe
import com.techdoctorbd.foodrecipe.utils.Constants.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}