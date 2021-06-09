package com.techdoctorbd.foodrecipe.ui.fragments.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.techdoctorbd.foodrecipe.R
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_DIET_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_MEAL_TYPE
import com.techdoctorbd.foodrecipe.viewmodels.RecipesViewModel
import java.util.*


class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel
    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.recipes_bottom_sheet, container, false)

        val mealTypeChipGroup = mView.findViewById<ChipGroup>(R.id.mealType_chipGroup)
        val dietTypeChipGroup = mView.findViewById<ChipGroup>(R.id.dietType_chipGroup)
        val button = mView.findViewById<Button>(R.id.btn_apply)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, {
            mealTypeChip = it.selectedMealType
            dietTypeChip = it.selectedDietType

            updateChip(it.selectedMealTypeId, mealTypeChipGroup)
            updateChip(it.selectedDietTypeId, dietTypeChipGroup)
        })

        mealTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = checkedId
        }

        dietTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = checkedId
        }

        button.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )

            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }

        return mView
    }

    private fun updateChip(selectedTypeId: Int, chipGroup: ChipGroup?) {
        if (selectedTypeId != 0) {
            try {
                chipGroup?.findViewById<Chip>(selectedTypeId)?.isChecked = true
            } catch (exception: Exception) {
                Log.d("RecipesBottomSheet", exception.message.toString())
            }
        }
    }
}