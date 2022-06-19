package com.rajith.swapiplanetapp.domain.usecases

import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val planetRepository: PlanetRepository
) {

    suspend operator fun invoke(page: Int): Flow<PlanetResult<List<Planet>>> {
        return planetRepository.getPlanet(page)
    }
}
