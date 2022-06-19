package com.rajith.swapiplanetapp.presentation.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajith.swapiplanetapp.R
import com.rajith.swapiplanetapp.databinding.ItemPlanetBinding
import com.rajith.swapiplanetapp.domain.models.Planet

class PlanetAdapter(private val planets: ArrayList<Planet>) :
    RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    var onItemClick: ((Planet) -> Unit)? = null
    var onBottomReached: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_planet, parent, false
        )
        val binding = ItemPlanetBinding.bind(itemView)
        return PlanetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet: Planet = planets[position]
        holder.bind(planet)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(planet)
        }
        if (position == planets.size - 2) {
            onBottomReached?.invoke()
        }
    }

    override fun getItemCount(): Int {
        return planets.size
    }

    fun addPlanets(items: List<Planet>) {
        val lastPlanetsSize: Int = planets.size
        planets.addAll(items)
        val newPlanetsSize: Int = planets.size
        if (newPlanetsSize > lastPlanetsSize) {
            notifyItemRangeChanged(lastPlanetsSize, newPlanetsSize)
        }
    }

    class PlanetViewHolder(private val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(planet: Planet) {
            binding.tvName.text = planet.name
            binding.tvClimate.text = planet.climate
        }
    }
}