package com.rajith.swapiplanetapp.di

import com.rajith.swapiplanetapp.data.datasource.remote.PlanetApi
import com.rajith.swapiplanetapp.data.datasource.remote.PlanetDataSourceImpl
import com.rajith.swapiplanetapp.data.repository.PlanetRepositoryImpl
import com.rajith.swapiplanetapp.domain.repository.PlanetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun providePlanetRepository(
        api: PlanetApi
    ): PlanetRepository {
        val planetDataSourceImpl = PlanetDataSourceImpl(api)
        return PlanetRepositoryImpl(planetDataSourceImpl)
    }
}
