package com.bsas.androiddevs.manejoerrores.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsas.androiddevs.manejoerrores.R
import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.databinding.ActivityMainBinding
import com.bsas.androiddevs.manejoerrores.ui.activity.adapter.MoviesRecyclerViewAdapter
import com.bsas.androiddevs.manejoerrores.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding

    private lateinit var moviesRecyclerViewAdapter : MoviesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        this.bindObservers()
        this.bindRecyclerAdapter()
    }

    private fun bindObservers() {
        this.viewModel.movies.observe(this, Observer { this.refreshMovieList(it) })
    }

    private fun refreshMovieList(movies: List<Movie>) {
        this.moviesRecyclerViewAdapter.refreshMovies(movies)
        this.binding.invalidateAll() //Neccesary?
    }

    private fun bindRecyclerAdapter() {
        val moviesRecyclerView = this.binding.moviesRecyclerView
        moviesRecyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(this)
        moviesRecyclerView.layoutManager = linearLayoutManager

        this.moviesRecyclerViewAdapter = MoviesRecyclerViewAdapter(listOf())
        moviesRecyclerView.adapter = this.moviesRecyclerViewAdapter
    }


}