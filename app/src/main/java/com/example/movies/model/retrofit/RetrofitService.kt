package com.example.movies.model.retrofit

import com.example.movies.model.modelclass.Movies
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("3/movie/popular?api_key=fbb6f0305c907e9c4a4646bf672cf6cd")
    suspend fun getPopularMovies():Response<Movies>

    companion object{
        var BASE_URL = "https://api.themoviedb.org/"
        var retrofitService: RetrofitService? = null

        fun getInstance():RetrofitService{
            if(retrofitService==null){
                retrofitService = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
