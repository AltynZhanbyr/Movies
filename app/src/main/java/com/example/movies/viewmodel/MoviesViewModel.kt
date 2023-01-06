package com.example.movies.viewmodel

import android.graphics.Movie
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.modelclass.MovieDetails
import com.example.movies.model.modelclass.MovieModel
import com.example.movies.model.modelclass.Movies
import com.example.movies.model.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesViewModel(private val retrofitService: RetrofitService):ViewModel() {
    val movies = MutableLiveData<List<MovieModel>>()
    val movie = MutableLiveData<MovieDetails>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitService.getPopularMovies()
            if(response.isSuccessful) {
                movies.postValue(response.body()!!.results)
                isSuccessful.postValue(true)
            }
            else
                isSuccessful.postValue(false)
        }
    }


    val isSuccessful = MutableLiveData(false)

   fun getMovieById(id:Int){
       viewModelScope.launch(Dispatchers.IO) {
           val response = retrofitService.getMovieById(id)
           if(response.isSuccessful) {
               movie.postValue(response.body()!!)
               isSuccessful.postValue(true)
           }

       }
   }
}