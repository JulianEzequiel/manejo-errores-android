package com.bsas.androiddevs.manejoerrores.repository.db.tasks;

import android.os.AsyncTask;

import com.bsas.androiddevs.manejoerrores.repository.db.dao.MovieDao;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;
import com.bsas.androiddevs.manejoerrores.repository.db.tasks.observer.GetAllMoviesTaskObserver;

import java.util.List;

public class GetAllMoviesTask extends AsyncTask<Void, Void, List<MovieDb>> {

    private MovieDao movieDao;
    private GetAllMoviesTaskObserver taskObserver;

    public GetAllMoviesTask(MovieDao movieDao, GetAllMoviesTaskObserver taskObserver) {
        this.movieDao = movieDao;
        this.taskObserver = taskObserver;
    }

    @Override
    protected List<MovieDb> doInBackground(Void... voids) {
        try {
            return this.movieDao.findAll();
        } catch (Exception e) {
            //TODO log exception
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MovieDb> movieDbs) {
        super.onPostExecute(movieDbs);

        if (movieDbs != null) {
            this.taskObserver.onMoviesObtained(movieDbs);
        } else {
            this.taskObserver.errorObtainingMovies();
        }
    }
}
