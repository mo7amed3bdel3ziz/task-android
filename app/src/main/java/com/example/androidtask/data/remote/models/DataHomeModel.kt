package com.example.androidtask.data.remote.models

data class DataHomeModel(
    var id: Int,
    var name: String,
    var email: String,
    var phone: String,
    var image: String,
    var logo: String,
    var description: String,
    var distance: String,
    var type: Int,
    var status: Int,
    var lat: String,
    var lng: String,
    var address: String,
    var appointments: String,
    var trending: Int,
    var popular: Int,
    var rate: String,
    var isFavorite: Boolean,
    var categories: ArrayList<Categories> = arrayListOf(),
    var token: String,
    var information: InformationModel,
    var product_categories: ArrayList<ProductCategoriesModel> = arrayListOf()
)
