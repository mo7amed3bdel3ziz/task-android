package com.example.androidtask.presentation.signUp

import androidx.lifecycle.ViewModel
import com.example.androidtask.data.remote.models.RegisterRequestModel
import com.example.androidtask.data.remote.api.ApiService
import com.example.androidtask.domain.usecases.AuthUseCases
import com.example.androidtask.utils.wrapWithFlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class SignUPViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    fun registerVM(registerRequestModel: RegisterRequestModel) = wrapWithFlowApi {
        useCases.registerAPI(registerRequestModel)
    }.flowOn(Dispatchers.IO)
}