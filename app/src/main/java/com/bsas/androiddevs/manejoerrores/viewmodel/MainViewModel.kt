package com.bsas.androiddevs.manejoerrores.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.manager.MovieManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val movieManager: MovieManager = MovieManager()

    private var viewModelJob = Job()
    private val viewScope = CoroutineScope(Dispatchers.Main + this.viewModelJob)


    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        this.getMovies()
    }

    private fun getMovies() {
        viewScope.launch {
            _movies.value = movieManager.getMovies()
        }
    }

    override fun onCleared() {
        super.onCleared()
        this.viewModelJob.cancel()
    }
}