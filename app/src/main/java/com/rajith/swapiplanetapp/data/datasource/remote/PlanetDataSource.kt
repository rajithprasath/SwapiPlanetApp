package com.rajith.swapiplanetapp.data.datasource.remote

import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.domain.models.Planet

interface PlanetDataSource {
    suspend fun getPlanets(page: Int): PlanetResult<List<Planet>>
}
