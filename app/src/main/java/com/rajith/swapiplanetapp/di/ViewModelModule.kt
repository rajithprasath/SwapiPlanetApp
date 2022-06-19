package com.rajith.swapiplanetapp.di

import com.rajith.swapiplanetapp.data.datasource.remote.PlanetApi
import com.rajith.swapiplanetapp.data.datasource.remote.PlanetDataSourceImpl
import com.rajith.swapiplanetapp.data.repository.PlanetRepositoryImpl
import com.rajith.swapiplanetapp.domain.repository.PlanetRepository
import com.rajith.swapiplanetapp.domain.usecases.GetPlanetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideGetPlanetUseCase(
        repository: PlanetRepository
    ): GetPlanetsUseCase {
        return GetPlanetsUseCase(repository)
    }

}