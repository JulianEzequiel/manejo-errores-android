package com.bsas.androiddevs.manejoerrores.repository.db.tasks;

import android.os.AsyncTask;

import com.bsas.androiddevs.manejoerrores.repository.db.dao.MovieDao;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;
import com.bsas.androiddevs.manejoerrores.repository.db.tasks.observer.SaveMoviesTaskObserver;

import java.util.List;

public class SaveMoviesTask extends AsyncTask<Void, Void, Boolean> {

    private MovieDao movieDao;
    private List<MovieDb> movieDbs;
    private SaveMoviesTaskObserver taskObserver;

    public SaveMoviesTask(MovieDao movieDao, List<MovieDb> movieDbs, SaveMoviesTaskObserver taskObserver) {
        this.movieDao = movieDao;
        this.movieDbs = movieDbs;
        this.taskObserver = taskObserver;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            this.movieDao.saveAll(this.movieDbs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //TODO log exception
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (!result) {
            this.taskObserver.onErrorSavingMovies();
        } else {
            this.taskObserver.onMoviesSaved();
        }
    }
}
