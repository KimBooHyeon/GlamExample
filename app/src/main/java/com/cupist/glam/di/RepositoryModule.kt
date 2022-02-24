package com.cupist.glam.di

import com.cupist.glam.domain.repository.UserRepository
import com.cupist.glam.network.ApiContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: ApiContainer): UserRepository {
        return UserRepository(api)
    }

}