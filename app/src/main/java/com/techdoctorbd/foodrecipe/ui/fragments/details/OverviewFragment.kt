package com.techdoctorbd.foodrecipe.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.techdoctorbd.foodrecipe.R
import com.techdoctorbd.foodrecipe.data.models.Result
import com.techdoctorbd.foodrecipe.databinding.FragmentOverviewBinding
import com.techdoctorbd.foodrecipe.utils.loadImageFromUrl


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result = args?.getSerializable("recipeBundle") as Result

        binding.mainImage.loadImageFromUrl(myBundle.image)
        binding.tvTitle.text = myBundle.title
        binding.tvLike.text = myBundle.aggregateLikes.toString()
        binding.tvTime.text = myBundle.readyInMinutes.toString()
        binding.tvSummery.text = myBundle.summary

        if (myBundle.vegetarian) {
            binding.ivVegetarian.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvVegetarian.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle.vegan) {
            binding.ivVegan.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvVegan.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle.glutenFree) {
            binding.ivGlutenFree.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvGlutenFree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle.dairyFree) {
            binding.ivDairyFree.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvDairyFree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle.veryHealthy) {
            binding.ivHealthy.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvHealthy.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle.cheap) {
            binding.ivCheap.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvCheap.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}