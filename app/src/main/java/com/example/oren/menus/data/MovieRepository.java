package com.example.oren.menus.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.oren.menus.webservice.Webservice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

//import javax.inject.Inject;
//import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;



public class MovieRepository {
    private String TAG = MovieRepository.class.getName();
    private Webservice webservice;
    private MovieDao movieDao;
    //private final Executor executor;
    private LiveData<List<Movie>> mAllMovies;



    public MovieRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.movieDao = db.movieDao();
        this.mAllMovies = movieDao.findAllMovies();

    }
 /*
    public LiveData<Movie> getMovie(long movieId) {
        refreshMovie(movieId);
        // return a LiveData directly from the database.
        return movieDao.findById(movieId);
    }

  private void refreshMovie(long movieId) {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable runnable) {

            }
        };

        executor.execute(() -> {
            // running in a background thread
            // check if movie was fetched recently
            boolean movieExists = movieDao.hasMovie(movieId)>0;
            if (!movieExists) {
                // refresh the data
                try {
                    Response response = webservice.getMovie(movieId).execute();
                    movieDao.insertMovie((Movie) response.body());

                }catch (IOException e){
                    Log.e(TAG, "refreshMovie: network access failed" );

                }
                // TODO check for error etc.
                // Update the database.The LiveData will automatically refresh so
                // we don't need to do anything else here besides updating the database
            }
        });
    }*/
/*
    public LiveData<Movie> getMovie(int movieId) {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<Movie> data = new MutableLiveData<>();
        webservice.getMovie(movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                // error case is left out for brevity
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: - failed to get data ");
                //TODO: handle error
            }
        });
        return data;
    }*/

    public void insertMovie(Movie movie) {
        new InsertAsyncTask(movieDao).execute(movie);
    }
    public LiveData<Movie> getMovie (Long movieId) {
        GetAsyncTask g = new GetAsyncTask(movieDao);
        g.execute(movieId);
        return g.movie;

    }

    private static class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        InsertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insertMovie(params[0]);
            return null;
        }
    }

    private static class GetAsyncTask extends AsyncTask<Long, Void, Void> {

        LiveData<Movie> movie;
        private MovieDao mAsyncTaskDao;

        GetAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            movie = mAsyncTaskDao.findById(params[0]);
            return null;
        }
    }

}