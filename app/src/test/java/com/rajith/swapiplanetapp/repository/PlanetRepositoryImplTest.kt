package com.rajith.swapiplanetapp.repository

import com.rajith.swapiplanetapp.data.PlanetDataSourceImplTest
import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class PlanetRepositoryImplTest(private val planetDataSourceImplTest: PlanetDataSourceImplTest) :
    PlanetRepository {

    override suspend fun getPlanet(page: Int): Flow<PlanetResult<List<Planet>>> {
        return flow {
            planetDataSourceImplTest.getPlanets(page).run {
                when (this) {
                    is PlanetResult.Success -> {
                        emit(PlanetResult.Success(data))
                    }
                    is PlanetResult.Error -> {
                        emit(PlanetResult.Error(exception))
                    }
                }
            }
        }.onStart { emit(PlanetResult.Loading) }
    }
}