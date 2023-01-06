package com.example.movies.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.MovieItemBinding
import com.example.movies.model.modelclass.MovieModel

class MoviesAdapter(private val movies:List<MovieModel>,private val context: Context):RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View,private val context:Context):RecyclerView.ViewHolder(view){
        private val binding = MovieItemBinding.bind(view)

        fun bind(movie:MovieModel){
            binding.movieTitle.text = movie.title
            binding.movieReleaseDate.text = movie.release_date
            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
                .into(binding.movieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}