package com.example.androidtask.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.R
import com.example.androidtask.UI.adapters.CategoryAdapter
import com.example.androidtask.UI.adapters.PopularAdapter
import com.example.androidtask.UI.adapters.TrendingAdapter
import com.example.androidtask.UI.viewmodels.HomeViewModel
import com.example.androidtask.UI.viewmodels.LoginViewModel
import com.example.androidtask.business.models.CaregoryItem
import com.example.androidtask.business.models.Categories
import com.example.androidtask.business.models.DataHomeModel
import com.example.androidtask.business.models.RequestModel
import com.example.androidtask.databinding.ActivityLoginBinding
import com.example.androidtask.databinding.ActivityMainBinding
import com.example.androidtask.network.State
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.notify

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()
    lateinit var token: String
    lateinit var username: String
    var dataHomeModel   : ArrayList<DataHomeModel> = arrayListOf()
    val categorySet = HashSet<CaregoryItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        token = intent.getStringExtra("token").toString()
        username = intent.getStringExtra("username").toString()
        binding.nameID.setText("Hello "+username)

        //call popular seller  api
        callPopularSeller()

        //call trending sellers api
        callTrendingSeller()

    //call get Base Categories
        getBaseCategories()

    }

    fun callPopularSeller(){
        lifecycleScope.launch() {
            viewModel.homeVM(
                token,
                29.1931,
                30.6421,
                1
            ).collect {

                when (it) {
                    is State.Loading -> {

                    }

                    is State.Success -> {
                        if (it.data.response_code ==200){
                            Toast.makeText(applicationContext, it.data.message, Toast.LENGTH_SHORT).show()

                            it.data.data.forEach { item ->
                                dataHomeModel.add(item)
                            }
                            binding.addressID.setText(dataHomeModel[0].address)
                            val popularAdapter = PopularAdapter()
                            popularAdapter.submitList(dataHomeModel)
                            binding.recyclerView2.adapter = popularAdapter
                            popularAdapter.notifyDataSetChanged()
                        }
                        else{
                            Toast.makeText(this@MainActivity, it.data.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    is State.Error -> {
                        Toast.makeText(this@MainActivity, it.messag, Toast.LENGTH_SHORT).show()
                        Log.d("VisitBranchWithoutPay", it.messag+"error")

                    }
                }
            }
        }
    }

    fun callTrendingSeller(){
        lifecycleScope.launch() {
            viewModel.trendingsellersVM(
                token,
                29.1931,
                30.6421,
                1
            ).collect {

                when (it) {
                    is State.Loading -> {

                    }

                    is State.Success -> {
                        if (it.data.response_code ==200){
                            it.data.data.forEach { item ->
                                dataHomeModel.add(item)
                            }
                            val trendingAdapter = TrendingAdapter()
                            trendingAdapter.submitList(dataHomeModel)
                            binding.recyclerView3.adapter = trendingAdapter
                            trendingAdapter.notifyDataSetChanged()

                        }
                        else{
                            Toast.makeText(this@MainActivity, it.data.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    is State.Error -> {
                        Toast.makeText(this@MainActivity, it.messag, Toast.LENGTH_SHORT).show()
                        Log.d("VisitBranchWithoutPay", it.messag+"error")

                    }
                }
            }
        }
    }


    fun getBaseCategories(){
        lifecycleScope.launch() {
            viewModel.getBaseCategoriesVM(
                token
            ).collect {

                when (it) {
                    is State.Loading -> {

                    }

                    is State.Success -> {
                        if (it.data.response_code ==200){


                            for (i in it.data.data) {
                                categorySet.addAll(listOf(i))
                                Log.d("VisitBranchWithoutPay","lll"+categorySet.toString())
                            }
                            val adapter = CategoryAdapter()
                            adapter.submitList(categorySet.toList())
                            binding.recyclerView.adapter = adapter
                            adapter.notifyDataSetChanged()

                        }
                        else{
                            Toast.makeText(this@MainActivity, it.data.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    is State.Error -> {
                        Toast.makeText(this@MainActivity, it.messag, Toast.LENGTH_SHORT).show()
                        Log.d("VisitBranchWithoutPay", it.messag+"error")

                    }
                }
            }
        }
    }
}