package com.nguyen.walmart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            Log.d("Tram", "loading...")
            _countries.postValue(Repository.fetchCountries())
        }
    }
}