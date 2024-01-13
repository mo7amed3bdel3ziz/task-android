package com.example.androidtask.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.example.androidtask.business.models.RegisterRequestModel
import com.example.androidtask.network.ApiService
import com.example.androidtask.network.wrapWithFlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class SignUPViewModel  @Inject constructor(
    private val api: ApiService

) : ViewModel() {
    fun registerVM( registerRequestModel: RegisterRequestModel) = wrapWithFlowApi {
        api.registerAPI(registerRequestModel)
    }.flowOn(Dispatchers.IO)
}