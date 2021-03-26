package com.techdoctorbd.foodrecipe.utils

import androidx.recyclerview.widget.DiffUtil
import com.techdoctorbd.foodrecipe.models.Result

class RecipesDiffUtils(
        val newList: List<Result>,
        val oldList: List<Result>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}