package com.example.androidtask.domain.repos

import com.example.androidtask.data.remote.models.LoginModel
import com.example.androidtask.data.remote.models.RegisterRequestModel
import com.example.androidtask.data.remote.models.RegisterationModel
import com.example.androidtask.data.remote.models.RequestModel
import kotlinx.coroutines.Deferred

interface AuthRepo {

    fun loginApI(requestModel: RequestModel): Deferred<LoginModel>

    fun registerAPI(registerRequestModel: RegisterRequestModel): Deferred<RegisterationModel>
}