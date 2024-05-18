package com.example.androidtask.domain.usecases

import com.example.androidtask.data.remote.models.BaseCategoryModel
import com.example.androidtask.data.remote.models.CaregoryItem
import com.example.androidtask.data.remote.models.DataHomeModel
import com.example.androidtask.data.remote.models.HomeModel
import com.example.androidtask.domain.repos.HomeRepo
import kotlinx.coroutines.Deferred

class HomeUseCases(private val repo: HomeRepo) {

    fun homeAPI(
        bearerToken: String,
        lat: Double,
        lng: Double,
        filter: Int
    ): Deferred<HomeModel<DataHomeModel>> {
        return repo.homeAPI(bearerToken, lat, lng, filter)
    }

    fun trendingSellersAPI(
        bearerToken: String,
        lat: Double,
        lng: Double,
        filter: Int
    ): Deferred<HomeModel<DataHomeModel>> {
        return repo.trendingSellersAPI(bearerToken, lat, lng, filter)
    }

    fun getHomeBaseCategoriesAPI(bearerToken: String): Deferred<HomeModel<BaseCategoryModel>> {
        return repo.getHomeBaseCategoriesAPI(bearerToken)
    }

    fun getBaseCategoriesAPI(bearerToken: String): Deferred<HomeModel<CaregoryItem>> {
        return repo.getBaseCategoriesAPI(bearerToken)
    }

    fun favoriteAPI(bearerToken: String, userId: Int): Deferred<HomeModel<CaregoryItem>> {
        return repo.favoriteAPI(bearerToken, userId)
    }
}