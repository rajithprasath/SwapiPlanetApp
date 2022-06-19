package com.rajith.swapiplanetapp.data.repository

import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.data.common.onFlowStarts
import com.rajith.swapiplanetapp.data.datasource.remote.PlanetDataSource
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val dataSource: PlanetDataSource
) : PlanetRepository {

    override suspend fun getPlanet(page: Int): Flow<PlanetResult<List<Planet>>> {
        return flow {
            dataSource.getPlanets(page).run {
                when (this) {
                    is PlanetResult.Success -> {
                        emit(PlanetResult.Success(data))
                    }
                    is PlanetResult.Error -> {
                        emit(PlanetResult.Error(exception))
                    }
                }
            }
        }.onFlowStarts()
    }

}