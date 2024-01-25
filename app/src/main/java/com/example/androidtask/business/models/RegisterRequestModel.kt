package com.example.androidtask.business.models

data class RegisterRequestModel(
    var name      : String,
    var email      : String,
    var password      : String,
    var phone      : String,
    var device_token      : String

)
