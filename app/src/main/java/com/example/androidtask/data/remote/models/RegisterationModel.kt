package com.example.androidtask.data.remote.models

data class RegisterationModel(
    var success: Boolean,
    var response_code: Int,
    var message: String,
    var data: RegisterationInfoModel
)
