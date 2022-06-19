package com.rajith.swapiplanetapp.presentation.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rajith.swapiplanetapp.data.PlanetDataSourceImplTest
import com.rajith.swapiplanetapp.data.common.onError
import com.rajith.swapiplanetapp.data.common.onSuccess
import com.rajith.swapiplanetapp.domain.usecases.GetPlanetsUseCase
import com.rajith.swapiplanetapp.repository.PlanetRepositoryImplTest
import com.rajith.swapiplanetapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlanetListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PlanetListViewModel

    @Before
    fun setup() {
        viewModel = PlanetListViewModel(
            GetPlanetsUseCase(
                PlanetRepositoryImplTest(
                    PlanetDataSourceImplTest()
                )
            )
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get list planets , returns success`() = mainCoroutineRule.runBlockingTest {
        viewModel.getPlanets()

        viewModel.planetsLiveData.value?.onSuccess { result ->
            assertThat(result).isNotNull()
            assertThat(result[0].name).isEqualTo("Tatooine")
            assertThat(result.size).isAtLeast(9)
        }?.onError { error ->
            assertThat(error).isNull()
        }

    }
}
