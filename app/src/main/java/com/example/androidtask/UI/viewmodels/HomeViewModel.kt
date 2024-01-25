package com.example.androidtask.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.example.androidtask.business.models.RequestModel
import com.example.androidtask.network.ApiService
import com.example.androidtask.network.wrapWithFlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiService

) : ViewModel() {

    fun homeVM(
        bearerToken: String, lat: Double, lng: Double, filter: Int
    ) = wrapWithFlowApi {
        api.homeAPI(bearerToken, lat, lng, filter)
    }.flowOn(Dispatchers.IO)


    fun trendingsellersVM(
        bearerToken: String, lat: Double, lng: Double, filter: Int
    ) = wrapWithFlowApi {
        api.trendingsellersAPI(bearerToken, lat, lng, filter)
    }.flowOn(Dispatchers.IO)


    fun getHomeBaseCategoriesVM(bearerToken: String) = wrapWithFlowApi {
        api.getHomeBaseCategoriesAPI(bearerToken)
    }.flowOn(Dispatchers.IO)

    fun getBaseCategoriesVM(bearerToken: String) = wrapWithFlowApi {
        api.getBaseCategoriesAPI(bearerToken)
    }.flowOn(Dispatchers.IO)


    fun favoriteVM(bearerToken: String,user_id:Int) = wrapWithFlowApi {
        api.favoriteAPI(bearerToken,user_id)
    }.flowOn(Dispatchers.IO)


}