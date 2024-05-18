package com.example.androidtask.di

import com.example.androidtask.data.AuthRepoImpl
import com.example.androidtask.data.HomeRepoImpl
import com.example.androidtask.data.remote.api.ApiService
import com.example.androidtask.domain.repos.AuthRepo
import com.example.androidtask.domain.repos.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ReposMoules {

    @Provides
    @Singleton
    fun provideAuthRepo(apiService: ApiService): AuthRepo {
        return AuthRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideHomeRepo(apiService: ApiService): HomeRepo {
        return HomeRepoImpl(apiService)
    }
}