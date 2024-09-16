package com.nguyen.walmart

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.walmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
        val myAdapter = CountriesAdapter(countries)
        val myLayoutManager = LinearLayoutManager(this@MainActivity)
        binding.recycler.apply {
            layoutManager = myLayoutManager
            adapter = myAdapter
            addItemDecoration(DividerItemDecoration(context, myLayoutManager.orientation))
        }

        val viewModel: MainViewModel by viewModels()
        viewModel.countries.observe(this) {
            countries.clear()
            if (it == null) {
                binding.tverror.isVisible = true
            } else {
                binding.tverror.isVisible = false
                countries.addAll(it)
            }
            myAdapter.notifyDataSetChanged()
        }

        binding.reload.setOnClickListener {
            viewModel.load()
        }
    }
}