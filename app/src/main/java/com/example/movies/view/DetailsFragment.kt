package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailsBinding
import com.example.movies.model.retrofit.RetrofitService
import com.example.movies.view.adapter.MoviesAdapter
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.ViewModelFactory

class DetailsFragment : Fragment() {

    private lateinit var binding:FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitService.getInstance()

        val viewModelFactory = ViewModelFactory(retrofitService)
        val movieViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        val id = DetailsFragmentArgs.fromBundle(requireArguments()).movieId
        if(id!=null){
            initValues(id, movieViewModel)
        }

        movieViewModel.isSuccessful.observe(viewLifecycleOwner){
            binding.detailsProgressBar.isVisible = !it
        }
    }

    private fun initValues(id:Long, viewModel: MoviesViewModel) {
        viewModel.getMovieById(id)
        viewModel.movie.observe(viewLifecycleOwner){movie->
            if(movie!=null){
                binding.also {
                    it.movieTitle.text = movie.title
                    it.movieOverview.text = movie.overview
                    it.movieReleaseDate.text = movie.release_date
                    it.movieVoteAverage.text = movie.vote_average.toString()
                    it.movieRuntime.text = movie.runtime.toString()
                    Glide
                        .with(requireNotNull(activity))
                        .load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
                        .into(it.movieImage)
                }
            }
        }
    }

}