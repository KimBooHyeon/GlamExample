package com.cupist.glam.di

import com.cupist.glam.network.ApiContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(retrofit: Retrofit): ApiContainer {
        return retrofit.create(ApiContainer::class.java)
    }

}