package com.rajith.swapiplanetapp.data.datasource.remote

import com.rajith.swapiplanetapp.data.common.DataSourceException
import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.data.common.RequestErrorHandler
import com.rajith.swapiplanetapp.domain.models.Planet
import javax.inject.Inject

class PlanetDataSourceImpl @Inject constructor(
    private val api: PlanetApi
) : PlanetDataSource {

    override suspend fun getPlanets(page: Int): PlanetResult<List<Planet?>> {
        return try {
            val result = api.getPlanets(page)
            if (result.isSuccessful) {
                PlanetResult.Success(result.body()!!.results)
            } else {
                PlanetResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            PlanetResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }


}