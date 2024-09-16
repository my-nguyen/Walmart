package com.nguyen.walmart

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.walmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val countries = mutableListOf<Country>()
        val countriesAdapter = CountriesAdapter(countries)
        val manager = LinearLayoutManager(this@MainActivity)
        binding.recycler.apply {
            layoutManager = manager
            adapter = countriesAdapter
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }

        val viewModel: MainViewModel by viewModels()
        viewModel.countries.observe(this, Observer {
            countries.addAll(it)
            countriesAdapter.notifyDataSetChanged()
        })
    }
}