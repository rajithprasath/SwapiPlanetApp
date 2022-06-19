package com.rajith.swapiplanetapp.data

import com.google.gson.Gson
import com.rajith.swapiplanetapp.R
import com.rajith.swapiplanetapp.data.common.DataSourceException
import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.data.datasource.remote.PlanetDataSource
import com.rajith.swapiplanetapp.data.response.PlanetResponse
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.util.getJson
import com.rajith.swapiplanetapp.utils.fromJsonToObjectType

class PlanetDataSourceImplTest : PlanetDataSource {

    override suspend fun getPlanets(page: Int): PlanetResult<List<Planet>> {
        val result =
            Gson().fromJsonToObjectType<PlanetResponse>(getJson("PlanetsApiResponse.json"))
        return if (result != null) {
            PlanetResult.Success(result.results)
        } else {
            PlanetResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

}