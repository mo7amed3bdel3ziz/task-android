package com.example.androidtask.di

import com.example.androidtask.domain.repos.AuthRepo
import com.example.androidtask.domain.repos.HomeRepo
import com.example.androidtask.domain.usecases.AuthUseCases
import com.example.androidtask.domain.usecases.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepo: AuthRepo): AuthUseCases {
        return AuthUseCases(authRepo)
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(homeRepo: HomeRepo): HomeUseCases {
        return HomeUseCases(homeRepo)
    }
}