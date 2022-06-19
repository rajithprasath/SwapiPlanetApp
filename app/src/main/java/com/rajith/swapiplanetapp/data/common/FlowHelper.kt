package com.rajith.swapiplanetapp.data.common

import com.rajith.swapiplanetapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/**
 * extension function for Flow Class to emit loading state before the flow starts
 */
fun <T> Flow<PlanetResult<T>>.onFlowStarts() =
    onStart {
        emit(PlanetResult.Loading)
    }.catch {
        emit(PlanetResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message)))
    }
