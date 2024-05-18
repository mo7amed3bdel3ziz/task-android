package com.example.androidtask.data.remote.api

import com.example.androidtask.data.remote.models.BaseCategoryModel
import com.example.androidtask.data.remote.models.CaregoryItem
import com.example.androidtask.data.remote.models.DataHomeModel
import com.example.androidtask.data.remote.models.HomeModel
import com.example.androidtask.data.remote.models.LoginModel
import com.example.androidtask.data.remote.models.RegisterRequestModel
import com.example.androidtask.data.remote.models.RegisterationModel
import com.example.androidtask.data.remote.models.RequestModel
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun loginApI(
        @Body requestModel: RequestModel
    ): Deferred<LoginModel>

    @Headers("Content-Type: application/json")
    @POST("api/client-register")
    fun registerAPI(
        @Body registerRequestModel: RegisterRequestModel
    ): Deferred<RegisterationModel>

    @Headers("Content-Type: application/json")
    @GET("api/popular-sellers")
    fun homeAPI(
        @Header("Authorization") bearerToken: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int
    ): Deferred<HomeModel<DataHomeModel>>

    @Headers("Content-Type: application/json")
    @GET("api/trending-sellers")
    fun trendingSellersAPI(
        @Header("Authorization") bearerToken: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int
    ): Deferred<HomeModel<DataHomeModel>>

    @Headers("Content-Type: application/json")
    @GET("api/home-base-categories")
    fun getHomeBaseCategoriesAPI(
        @Header("Authorization") bearerToken: String
    ): Deferred<HomeModel<BaseCategoryModel>>

    @Headers("Content-Type: application/json")
    @GET("api/base-categories")
    fun getBaseCategoriesAPI(
        @Header("Authorization") bearerToken: String
    ): Deferred<HomeModel<CaregoryItem>>

    @Headers("Content-Type: application/json")
    @POST("api/favorite")
    fun favoriteAPI(
        @Header("Authorization") bearerToken: String,
        @Query("user_id") userId: Int
    ): Deferred<HomeModel<CaregoryItem>>
}