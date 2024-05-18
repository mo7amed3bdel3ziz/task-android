package com.example.androidtask.data.remote.models

data class LoginModel(
    var success: Boolean,
    var response_code: Int,
    var message: String,
    var data: UserInfoModel
)
