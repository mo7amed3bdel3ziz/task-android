package com.example.androidtask.business.models

data class RegisterationModel(
    var success      : Boolean,
    var response_code : Int,
    var message      : String,
    var data         : RegisterationInfoModel
)
