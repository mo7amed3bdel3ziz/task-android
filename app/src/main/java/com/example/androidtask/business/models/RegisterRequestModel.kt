package com.example.androidtask.business.models

data class RegisterRequestModel(
    var name      : String,
    var email      : String,
    var password      : Int,
    var phone      : Int,
    var device_token      : String

)
