package com.example.androidtask.data.remote.models

data class BaseCategoryModel(
    var data: ArrayList<CaregoryItem> = arrayListOf(),
    var cart_count: Int
)
