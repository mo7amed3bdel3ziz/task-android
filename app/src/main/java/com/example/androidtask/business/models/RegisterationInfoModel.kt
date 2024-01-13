package com.example.androidtask.business.models

data class RegisterationInfoModel(
    var id        : Int  ,
    var name      : String,
    var email     : String,
    var phone     : String,
    var image     : String,
    var type      : Int  ,
    var status    : Int  ,
    var balance   : String,
    var addresses : ArrayList<String> = arrayListOf(),
    var token     : String
)
