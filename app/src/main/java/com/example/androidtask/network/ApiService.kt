package com.example.androidtask.network

import com.example.androidtask.business.models.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ApiService {


    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun LoginApI(
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
    fun trendingsellersAPI(
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
        @Query("user_id") user_id: Int
    ): Deferred<HomeModel<CaregoryItem>>


}