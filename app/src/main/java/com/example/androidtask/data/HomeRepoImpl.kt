package com.example.androidtask.data

import com.example.androidtask.data.remote.api.ApiService
import com.example.androidtask.domain.repos.HomeRepo

class HomeRepoImpl(private val api: ApiService) : HomeRepo {

    override fun homeAPI(
        bearerToken: String,
        lat: Double,
        lng: Double,
        filter: Int
    ) = api.homeAPI(bearerToken, lat, lng, filter)

    override fun trendingSellersAPI(
        bearerToken: String,
        lat: Double,
        lng: Double,
        filter: Int
    ) = api.trendingSellersAPI(bearerToken, lat, lng, filter)

    override fun getHomeBaseCategoriesAPI(bearerToken: String) =
        api.getHomeBaseCategoriesAPI(bearerToken)

    override fun getBaseCategoriesAPI(bearerToken: String) = api.getBaseCategoriesAPI(bearerToken)

    override fun favoriteAPI(bearerToken: String, userId: Int) =
        api.favoriteAPI(bearerToken, userId)
}