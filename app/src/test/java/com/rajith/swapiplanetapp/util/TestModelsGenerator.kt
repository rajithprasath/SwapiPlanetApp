package com.rajith.swapiplanetapp.util

import com.google.gson.Gson
import com.rajith.swapiplanetapp.data.response.PlanetResponse
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.utils.fromJsonToObjectType
import java.io.File


/**
 * Helper function which will load JSON from
 * the path specified
 *
 * @param path : Path of JSON file
 * @return json : JSON from file at given path
 */

class TestModelsGenerator {
    private var recipes: PlanetResponse

    init {
        recipes =
            Gson().fromJsonToObjectType<PlanetResponse>(getJson("PlanetsApiResponse.json"))
        print("this is $recipes")
    }

    fun generatePlanetResponse(): PlanetResponse {
        return recipes
    }

    fun generatePlanetResponseWithEmptyList(): PlanetResponse {
        return PlanetResponse(0, "", "", arrayListOf())
    }

    fun generatePlanetModel(): Planet {
        return recipes.results[0]
    }

    fun getNameOfPlanet(): String {
        return recipes.results[0].name
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}

