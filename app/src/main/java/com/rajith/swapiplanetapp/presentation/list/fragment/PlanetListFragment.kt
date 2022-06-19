package com.rajith.swapiplanetapp.presentation.list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rajith.swapiplanetapp.R
import com.rajith.swapiplanetapp.data.common.PlanetResult
import com.rajith.swapiplanetapp.databinding.FragmentPlanetListBinding
import com.rajith.swapiplanetapp.presentation.list.adapter.PlanetAdapter
import com.rajith.swapiplanetapp.presentation.list.viewmodel.PlanetListViewModel
import com.rajith.swapiplanetapp.utils.ConnectionLiveData
import com.rajith.swapiplanetapp.utils.hide
import com.rajith.swapiplanetapp.utils.show
import com.rajith.swapiplanetapp.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
class PlanetListFragment : Fragment() {
    private val planetListViewModel: PlanetListViewModel by viewModels()
    private lateinit var binding: FragmentPlanetListBinding
    private lateinit var planetAdapter: PlanetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        checkInternetAvailability()
        observeData()
    }

    private fun checkInternetAvailability() {
        context?.let { context ->
            ConnectionLiveData(context).observe(viewLifecycleOwner) {
                if (!it) activity?.toast(getString(R.string.error_network))
            }
        }
    }

    private fun observeData() {
        planetListViewModel.planetsLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {

                is PlanetResult.Success -> {
                    response.data.let { planetList ->
                        planetAdapter.addPlanets(planetList)
                    }
                    hideProgressBar()
                }
                is PlanetResult.Error -> {
                    hideProgressBar()
                    when (response.exception.messageResource) {
                        is Int -> setError(getString(response.exception.messageResource))
                        is ResponseBody? -> response.exception.messageResource?.string()
                            ?.let { setError(it) }
                    }
                }
                is PlanetResult.Loading -> {
                    showProgressBar()
                }
            }
        }
    }


    private fun hideProgressBar() {
        binding.progressBar.hide()
        binding.progressBarPaging.hide()
    }

    private fun showProgressBar() {
        if (planetListViewModel.page > 2)
        binding.progressBarPaging.show()
    }

    private fun setupRecyclerView() {
        planetAdapter = PlanetAdapter(ArrayList())

        binding.rvPlanets.layoutManager = LinearLayoutManager(activity)
        binding.rvPlanets.adapter = planetAdapter

        planetAdapter.onBottomReached = {
            lifecycleScope.launch {
                planetListViewModel.getPlanets()
            }
        }

        planetAdapter.onItemClick = { it ->
            val bundle = Bundle().apply {
                putParcelable("planet", it)
            }
            findNavController().navigate(
                R.id.action_planetListFragment_to_planetDetailFragment,
                bundle
            )
        }

    }

    private fun setError(error: String) {
        binding.progressBar.hide()
        val snackBar: Snackbar =
            Snackbar.make(binding.root, error, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(getString(R.string.retry)) {
            getData()
            snackBar.dismiss()
        }
    }

    private fun getData(){
        lifecycleScope.launch {
            planetListViewModel.getPlanets()
        }
    }

}
