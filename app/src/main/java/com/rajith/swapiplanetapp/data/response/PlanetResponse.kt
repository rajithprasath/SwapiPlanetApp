package com.rajith.swapiplanetapp.data.response

import com.rajith.swapiplanetapp.domain.models.Planet

data class PlanetResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Planet>
)