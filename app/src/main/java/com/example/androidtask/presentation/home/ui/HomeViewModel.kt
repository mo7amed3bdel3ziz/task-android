package com.example.androidtask.presentation.home.ui

import androidx.lifecycle.ViewModel
import com.example.androidtask.domain.usecases.HomeUseCases
import com.example.androidtask.utils.wrapWithFlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: HomeUseCases) : ViewModel() {

    fun homeVM(
        bearerToken: String, lat: Double, lng: Double, filter: Int
    ) = wrapWithFlowApi {
        useCases.homeAPI(bearerToken, lat, lng, filter)
    }.flowOn(Dispatchers.IO)

    fun trendingSellersVM(
        bearerToken: String, lat: Double, lng: Double, filter: Int
    ) = wrapWithFlowApi {
        useCases.trendingSellersAPI(bearerToken, lat, lng, filter)
    }.flowOn(Dispatchers.IO)

    fun getHomeBaseCategoriesVM(bearerToken: String) = wrapWithFlowApi {
        useCases.getHomeBaseCategoriesAPI(bearerToken)
    }.flowOn(Dispatchers.IO)

    fun getBaseCategoriesVM(bearerToken: String) = wrapWithFlowApi {
        useCases.getBaseCategoriesAPI(bearerToken)
    }.flowOn(Dispatchers.IO)


    fun favoriteVM(bearerToken: String, userId: Int) = wrapWithFlowApi {
        useCases.favoriteAPI(bearerToken, userId)
    }.flowOn(Dispatchers.IO)

}