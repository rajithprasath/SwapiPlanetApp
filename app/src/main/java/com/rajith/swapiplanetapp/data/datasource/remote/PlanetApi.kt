package com.rajith.swapiplanetapp.data.datasource.remote

import com.rajith.swapiplanetapp.data.common.PATH_PLANETS
import com.rajith.swapiplanetapp.data.response.PlanetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApi {

    @GET(PATH_PLANETS)
    suspend fun getPlanets(@Query("page") page: Int): Response<PlanetResponse>

}
