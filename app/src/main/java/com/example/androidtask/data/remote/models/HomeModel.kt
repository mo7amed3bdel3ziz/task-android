package com.example.androidtask.data.remote.models

data class HomeModel<T>(
    var success: Boolean,
    var response_code: Int,
    var message: String,
    var data: ArrayList<T> = arrayListOf()
)
