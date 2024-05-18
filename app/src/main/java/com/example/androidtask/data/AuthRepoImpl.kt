package com.example.androidtask.data

import com.example.androidtask.data.remote.api.ApiService
import com.example.androidtask.data.remote.models.RegisterRequestModel
import com.example.androidtask.data.remote.models.RequestModel
import com.example.androidtask.domain.repos.AuthRepo


class AuthRepoImpl(private val api: ApiService) : AuthRepo {

    override fun loginApI(requestModel: RequestModel) = api.loginApI(requestModel)
    
    override fun registerAPI(registerRequestModel: RegisterRequestModel) =
        api.registerAPI(registerRequestModel)
}