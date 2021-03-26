package com.techdoctorbd.foodrecipe.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.techdoctorbd.foodrecipe.R
import com.techdoctorbd.foodrecipe.adapters.FoodRecipesAdapter
import com.techdoctorbd.foodrecipe.utils.NetworkResult
import com.techdoctorbd.foodrecipe.viewmodels.MainViewModel
import com.techdoctorbd.foodrecipe.viewmodels.RecipesViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { FoodRecipesAdapter() }
    private lateinit var mView: View
    private lateinit var recyclerView: ShimmerRecyclerView
    private lateinit var actionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        recyclerView = mView.findViewById(R.id.recyclerView)
        actionButton = mView.findViewById(R.id.floatingActionButton)

        setupRecyclerView()
        requestApiData()

        return mView
    }

    private fun showShimmerEffect() {
        recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        recyclerView.hideShimmer()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        showShimmerEffect()
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())

        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.also { mAdapter.setData(it) }
                }
            }
        })
    }
}