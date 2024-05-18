package com.example.androidtask.presentation.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.databinding.FragmentMainBinding
import com.example.androidtask.presentation.home.CategoryAdapter
import com.example.androidtask.presentation.home.PopularAdapter
import com.example.androidtask.presentation.home.TrendingAdapter
import com.example.androidtask.data.remote.models.CaregoryItem
import com.example.androidtask.data.remote.models.DataHomeModel
import com.example.androidtask.utils.network.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var token: String
    private lateinit var username: String
    private var dataHomeModel: ArrayList<DataHomeModel> = arrayListOf()
    private val categorySet = HashSet<CaregoryItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments
        token = requireArguments().getString("token").toString()
        username = requireArguments().getString("username").toString()
        binding.nameID.text = "Hello $username"

        callPopularSeller()
        callTrendingSeller()
        getBaseCategories()
    }

    private fun callPopularSeller() {
        lifecycleScope.launch {
            viewModel.homeVM(token, 29.1931, 30.6421, 1).collect { it ->
                when (it) {
                    is State.Loading -> {
                        // Handle loading state
                    }
                    is State.Success -> {
                        if (it.data.response_code == 200) {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT)
                                .show()
                            dataHomeModel.addAll(it.data.data)
                            binding.addressID.text = dataHomeModel[0].address
                            val popularAdapter = PopularAdapter()
                            popularAdapter.submitList(dataHomeModel)
                            binding.recyclerView2.adapter = popularAdapter
                        } else {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is State.Error -> {
                        Toast.makeText(requireContext(), it.messag, Toast.LENGTH_SHORT).show()
                        Log.d("VisitBranchWithoutPay", "${it.messag} error")
                    }
                }
            }
        }
    }

    private fun callTrendingSeller() {
        lifecycleScope.launch {
            viewModel.trendingSellersVM(token, 29.1931, 30.6421, 1).collect { it ->
                when (it) {
                    is State.Loading -> {
                        // Handle loading state
                    }
                    is State.Success -> {
                        if (it.data.response_code == 200) {
                            dataHomeModel.addAll(it.data.data)
                            val trendingAdapter = TrendingAdapter()
                            trendingAdapter.submitList(dataHomeModel)
                            binding.recyclerView3.adapter = trendingAdapter
                        } else {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is State.Error -> {
                        Toast.makeText(requireContext(), it.messag, Toast.LENGTH_SHORT).show()
                        Log.d("VisitBranchWithoutPay", "${it.messag} error")
                    }
                }
            }
        }
    }

    private fun getBaseCategories() {
        lifecycleScope.launch {
            viewModel.getBaseCategoriesVM(token).collect { it ->
                when (it) {
                    is State.Loading -> {
                        // Handle loading state
                    }
                    is State.Success -> {
                        if (it.data.response_code == 200) {
                            categorySet.addAll(it.data.data)
                            Log.d("VisitBranchWithoutPay", "lll $categorySet")
                            val adapter = CategoryAdapter()
                            adapter.submitList(categorySet.toList())
                            binding.recyclerView.adapter = adapter
                        } else {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is State.Error -> {
                        Toast.makeText(requireContext(), it.messag, Toast.LENGTH_SHORT).show()
                        Log.d("VisitBranchWithoutPay", "${it.messag} error")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
