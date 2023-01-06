package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailsBinding
import com.example.movies.model.retrofit.RetrofitService
import com.example.movies.view.adapter.MoviesAdapter
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.ViewModelFactory

class DetailsFragment : Fragment() {

    private lateinit var adapter:MoviesAdapter
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
        val id = DetailsFragmentArgs.fromBundle(requireArguments()).movieId

        binding.movieTitle.text = id.toString()
    }

}