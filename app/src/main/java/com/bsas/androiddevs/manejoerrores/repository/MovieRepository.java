package com.bsas.androiddevs.manejoerrores.repository;

import android.content.Context;

import com.bsas.androiddevs.manejoerrores.common.exception.DbException;
import com.bsas.androiddevs.manejoerrores.common.util.CollectionsConverter;
import com.bsas.androiddevs.manejoerrores.manager.listener.GetMoviesObserver;
import com.bsas.androiddevs.manejoerrores.repository.api.MoviesApiClient;
import com.bsas.androiddevs.manejoerrores.repository.api.callback.EndpointCallback;
import com.bsas.androiddevs.manejoerrores.repository.api.dto.GetMoviesResponse;
import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto;
import com.bsas.androiddevs.manejoerrores.repository.db.access.impl.MovieDao;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;
import com.bsas.androiddevs.manejoerrores.repository.db.tasks.GetAllMoviesTask;
import com.bsas.androiddevs.manejoerrores.repository.db.tasks.SaveMoviesTask;
import com.bsas.androiddevs.manejoerrores.repository.db.tasks.observer.GetAllMoviesTaskObserver;
import com.bsas.androiddevs.manejoerrores.repository.db.tasks.observer.SaveMoviesTaskObserver;

import java.util.List;

public class MovieRepository {

    private MoviesApiClient moviesApiClient;
    private MovieDao movieDao;

    public MovieRepository(Context context) {
        this.moviesApiClient = MoviesApiClient.getInstance();
        this.movieDao = new MovieDao(context);
    }

    public void getMovies(GetMoviesObserver observer) throws DbException {
        if (this.movieDao.countAll() == 0) {
            this.getMoviesFromApiAndSave(observer);
        }
        this.returnMoviesFromDb(observer);
    }

    private void getMoviesFromApiAndSave(final GetMoviesObserver observer) {
        this.moviesApiClient.getMovies(new EndpointCallback<GetMoviesResponse>() {
            @Override
            public void onSuccess(GetMoviesResponse data) {
                MovieRepository.this.saveMoviesToDbAndReturn(data.getMovies(), observer);
            }

            @Override
            public void onFailure(Throwable t) {
                observer.onErrorObtainingMovies();
            }
        });
    }

    private void saveMoviesToDbAndReturn(List<MovieDto> movieDtos, final GetMoviesObserver observer) {
        List<MovieDb> movieDbs = CollectionsConverter.movieDtosToMovieDbs(movieDtos);
        new SaveMoviesTask(this.movieDao, movieDbs, new SaveMoviesTaskObserver() {
            @Override
            public void onMoviesSaved() {
                MovieRepository.this.returnMoviesFromDb(observer);
            }

            @Override
            public void onErrorSavingMovies() {
                observer.onErrorObtainingMovies();
            }
        }).execute();
    }

    private void returnMoviesFromDb(final GetMoviesObserver observer) {
        new GetAllMoviesTask(this.movieDao, new GetAllMoviesTaskObserver() {
            @Override
            public void onMoviesObtained(List<MovieDb> movieDbs) {
                observer.onMoviesObtained(CollectionsConverter.movieDbsToMovies(movieDbs));
            }

            @Override
            public void errorObtainingMovies() {
                observer.onErrorObtainingMovies();
            }
        }).execute();
    }
}
