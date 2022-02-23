package com.cupist.glam.di

import com.cupist.glam.domain.repository.PersonRepository
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
    fun provideSearchRepository(api: ApiContainer): PersonRepository {
        return PersonRepository(api)
    }

}