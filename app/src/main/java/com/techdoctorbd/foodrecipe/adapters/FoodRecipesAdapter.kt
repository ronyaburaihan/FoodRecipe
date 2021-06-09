package com.techdoctorbd.foodrecipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techdoctorbd.foodrecipe.databinding.LayoutRecipeItemBinding
import com.techdoctorbd.foodrecipe.data.models.FoodRecipe
import com.techdoctorbd.foodrecipe.data.models.Result
import com.techdoctorbd.foodrecipe.utils.RecipesDiffUtils

class FoodRecipesAdapter : RecyclerView.Adapter<FoodRecipesAdapter.RecipesViewHolder>() {

    private var recipes = emptyList<Result>()

    class RecipesViewHolder(private val binding: LayoutRecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(LayoutRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val result = recipes[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: FoodRecipe) {
        val recipesDiffUtils = RecipesDiffUtils(newData.results, recipes)
        val diffUtils = DiffUtil.calculateDiff(recipesDiffUtils)
        recipes = newData.results
        diffUtils.dispatchUpdatesTo(this)
    }
}