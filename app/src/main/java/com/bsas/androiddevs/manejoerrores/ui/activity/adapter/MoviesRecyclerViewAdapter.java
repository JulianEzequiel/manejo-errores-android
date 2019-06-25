package com.bsas.androiddevs.manejoerrores.ui.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsas.androiddevs.manejoerrores.R;
import com.bsas.androiddevs.manejoerrores.common.Movie;

import java.util.List;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder> {

    private List<Movie> movies;

    public MoviesRecyclerViewAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int index) {
        Movie movie = this.movies.get(index);
        movieViewHolder.movieTitleText.setText(movie.getTitle());
        movieViewHolder.movieYearText.setText(String.valueOf(movie.getYear()));
    }

    @Override
    public int getItemCount() {
        if (this.movies == null) {
            return 0;
        }
        return this.movies.size();
    }

    public void refreshMovies(List<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitleText;
        TextView movieYearText;

        MovieViewHolder(@NonNull LinearLayout viewLayout) {
            super(viewLayout);

            this.movieTitleText = viewLayout.findViewById(R.id.movie_name);
            this.movieYearText = viewLayout.findViewById(R.id.movie_year);
        }

    }
}
