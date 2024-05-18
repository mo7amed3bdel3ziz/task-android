package com.example.androidtask.presentation.login

import androidx.lifecycle.ViewModel
import com.example.androidtask.data.remote.models.RequestModel
import com.example.androidtask.domain.usecases.AuthUseCases
import com.example.androidtask.utils.wrapWithFlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCases: AuthUseCases) : ViewModel() {

    fun loginVM(requestModel: RequestModel) = wrapWithFlowApi {
        useCases.loginApI(requestModel)
    }.flowOn(Dispatchers.IO)

}