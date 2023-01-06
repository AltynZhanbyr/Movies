package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.model.retrofit.RetrofitService

class ViewModelFactory(private val retrofitService: RetrofitService):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesViewModel::class.java))
            return MoviesViewModel(retrofitService) as T
        else
            throw IllegalArgumentException("Unknown ViewModel")
    }
}