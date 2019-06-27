package com.bsas.androiddevs.manejoerrores.ui.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bsas.androiddevs.manejoerrores.R
import com.bsas.androiddevs.manejoerrores.common.Movie

class MoviesRecyclerViewAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): MovieViewHolder {
        val linearLayout = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false) as LinearLayout
        return MovieViewHolder(linearLayout)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, index: Int) {
        val (title, year) = this.movies[index]
        movieViewHolder.movieTitleText.text = title
        movieViewHolder.movieYearText.text = year.toString()
    }

    override fun getItemCount(): Int = this.movies.size

    fun refreshMovies(movies: List<Movie>) {
        this.movies = movies
        this.notifyDataSetChanged()
    }

    inner class MovieViewHolder(viewLayout: LinearLayout) : RecyclerView.ViewHolder(viewLayout) {
        var movieTitleText: TextView
        var movieYearText: TextView

        init {

            this.movieTitleText = viewLayout.findViewById(R.id.movie_name)
            this.movieYearText = viewLayout.findViewById(R.id.movie_year)
        }

    }
}
