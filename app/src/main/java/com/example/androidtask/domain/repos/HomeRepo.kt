package com.example.androidtask.domain.repos

import com.example.androidtask.data.remote.models.BaseCategoryModel
import com.example.androidtask.data.remote.models.CaregoryItem
import com.example.androidtask.data.remote.models.DataHomeModel
import com.example.androidtask.data.remote.models.HomeModel
import kotlinx.coroutines.Deferred


interface HomeRepo {

    fun homeAPI(
        bearerToken: String,
        lat: Double,
        lng: Double,
        filter: Int
    ): Deferred<HomeModel<DataHomeModel>>

    fun trendingSellersAPI(
        bearerToken: String,
        lat: Double,
        lng: Double,
        filter: Int
    ): Deferred<HomeModel<DataHomeModel>>

    fun getHomeBaseCategoriesAPI(bearerToken: String): Deferred<HomeModel<BaseCategoryModel>>

    fun getBaseCategoriesAPI(bearerToken: String): Deferred<HomeModel<CaregoryItem>>

    fun favoriteAPI(bearerToken: String, userId: Int): Deferred<HomeModel<CaregoryItem>>
}