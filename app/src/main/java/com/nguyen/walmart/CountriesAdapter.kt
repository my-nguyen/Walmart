package com.nguyen.walmart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.walmart.databinding.ItemCountryBinding

class CountriesAdapter(val countries: List<Country>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                name.text = country.name ?: ""
                region.text = country.region ?: ""
                code.text = country.code ?: ""
                capital.text = country.capital ?: ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}