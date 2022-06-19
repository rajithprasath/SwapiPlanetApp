package com.rajith.swapiplanetapp.domain.repository

import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.domain.models.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    suspend fun getPlanet(page: Int): Flow<PlanetResult<List<Planet?>>>
}
