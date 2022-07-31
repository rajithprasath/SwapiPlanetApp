package com.rajith.swapiplanetapp.presentation.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.domain.usecases.GetPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {
    val planetsLiveData: MutableLiveData<PlanetResult<List<Planet>>> = MutableLiveData()

    var page = 1

    init {
        viewModelScope.launch {
            getPlanets()
        }
    }

    suspend fun getPlanets() {
        getPlanetsUseCase(page)
            .onEach { result ->
                handleCitiesResponse(result, planetsLiveData)
            }.launchIn(viewModelScope)
    }


    private fun handleCitiesResponse(
        result: PlanetResult<List<Planet>>,
        mutableData: MutableLiveData<PlanetResult<List<Planet>>>
    ) {
        when (result) {
            is PlanetResult.Success -> {
                page += 1
                mutableData.postValue(PlanetResult.Success(result.data))
            }
            is PlanetResult.Error -> {
                mutableData.postValue(result.exception.let {
                    PlanetResult.Error(it) })
            }
            is PlanetResult.Loading -> {
                mutableData.postValue(PlanetResult.Loading)
            }
        }
    }

}