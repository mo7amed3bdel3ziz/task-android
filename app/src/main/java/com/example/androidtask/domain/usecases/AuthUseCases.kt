package com.example.androidtask.domain.usecases

import com.example.androidtask.data.remote.models.LoginModel
import com.example.androidtask.data.remote.models.RegisterRequestModel
import com.example.androidtask.data.remote.models.RegisterationModel
import com.example.androidtask.data.remote.models.RequestModel
import com.example.androidtask.domain.repos.AuthRepo
import kotlinx.coroutines.Deferred

class AuthUseCases(private val repo: AuthRepo) {

    fun loginApI(requestModel: RequestModel): Deferred<LoginModel> {
        return repo.loginApI(requestModel)
    }

    fun registerAPI(registerRequestModel: RegisterRequestModel): Deferred<RegisterationModel> {
        return repo.registerAPI(registerRequestModel)
    }
}