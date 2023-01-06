package com.example.movies.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movies.model.modelclass.MovieModel

class MovieDiffItemCallback:DiffUtil.ItemCallback<MovieModel>(){
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem==newItem
    }

}
