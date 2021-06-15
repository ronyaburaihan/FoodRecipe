package com.techdoctorbd.foodrecipe.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.techdoctorbd.foodrecipe.R
import com.techdoctorbd.foodrecipe.data.models.Result
import com.techdoctorbd.foodrecipe.ui.fragments.RecipesFragmentDirections

class RecipesItemBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("onRecipeItemClickListener")
        fun onRecipeItemClickListener(recipeItemLayout: ConstraintLayout, result: Result) {
            Log.d("onRecipeClickListener", "Recipe Item Clicked")
            recipeItemLayout.setOnClickListener {
                try {
                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                    recipeItemLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

        @JvmStatic
        @BindingAdapter("setNumberOfLikes")
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @JvmStatic
        @BindingAdapter("setNumberOfMinutes")
        fun setNumberOfMinutes(textView: TextView, minutes: Int) {
            textView.text = minutes.toString()
        }


        @JvmStatic
        @BindingAdapter("applyVegansColor")
        fun applyVegansColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is TextView -> {
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }

                    is ImageView -> {
                        view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                    }
                }
            }
        }

        @JvmStatic
        @BindingAdapter("loadImageFromUrl")
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(true)
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }
    }
}