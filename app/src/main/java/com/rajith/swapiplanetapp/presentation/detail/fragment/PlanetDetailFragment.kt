package com.rajith.swapiplanetapp.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rajith.swapiplanetapp.databinding.FragmentPlanetDetailBinding
import com.rajith.swapiplanetapp.domain.models.Planet
import com.rajith.swapiplanetapp.utils.hide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetDetailFragment : Fragment() {
    private val args: PlanetDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlanetDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPlanetDetails(args.planet)
        binding.detailToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun hideProgressBar() {
        binding.progressBar.hide()
    }

    private fun showPlanetDetails(planet: Planet) {
        binding.tbTitle.title = planet.name
        binding.tvClimate.text = planet.climate
        binding.tvOrbital.text = planet.orbital_period
        binding.tvGravity.text = planet.gravity
        hideProgressBar()
    }
}