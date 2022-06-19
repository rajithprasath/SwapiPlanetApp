package com.rajith.swapiplanetapp.data.common

/**
 * Class to maintain loading, success and error states
 */
sealed class PlanetResult<out T> {
    data class Success<out T>(val data: T) : PlanetResult<T>()
    data class Error(val exception: DataSourceException) : PlanetResult<Nothing>()
    object Loading : PlanetResult<Nothing>()
}

inline fun <T : Any> PlanetResult<T>.onSuccess(action: (T) -> Unit): PlanetResult<T> {
    if (this is PlanetResult.Success) action(data)
    return this
}

inline fun <T : Any> PlanetResult<T>.onError(action: (DataSourceException) -> Unit): PlanetResult<T> {
    if (this is PlanetResult.Error) action(exception)
    return this
}

inline fun <T : Any> PlanetResult<T>.onLoading(action: () -> Unit): PlanetResult<T> {
    if (this is PlanetResult.Loading) action()
    return this
}
