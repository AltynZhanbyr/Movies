package com.example.movies.model.modelclass

data class Movies(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)