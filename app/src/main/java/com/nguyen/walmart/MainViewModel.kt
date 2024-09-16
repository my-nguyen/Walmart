package com.nguyen.walmart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    init {
        Log.d("MainViewModel", "init...")
        CoroutineScope(Dispatchers.Main).launch {
            _countries.value = Network.countryService().fetchCountries()
        }
    }
}