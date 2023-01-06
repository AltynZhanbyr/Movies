package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.view.isVisible
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieListBinding
import com.example.movies.model.modelclass.MovieModel
import com.example.movies.model.retrofit.RetrofitService
import com.example.movies.view.adapter.MoviesAdapter
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MovieListFragment : Fragment(),MoviesAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitService.getInstance()

        val viewModelFactory = ViewModelFactory(retrofitService)
        val movieViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        val activity = requireNotNull(activity)

        adapter = MoviesAdapter(activity,this)

        movieViewModel.movies.observe(viewLifecycleOwner){
            if(it!=null){
               adapter.submitList(it)
               binding.movieList.adapter = adapter
               adapter.notifyDataSetChanged()
            }
        }

        movieViewModel.isSuccessful.observe(viewLifecycleOwner){success->
            binding.progressCircular.isVisible = !success
        }

       // movieViewModel.getPopularMovies()
    }

    override fun onMovieClickListener(movie: MovieModel) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToDetailsFragment(movie.id)
        findNavController().navigate(action)
    }
}