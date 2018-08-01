package com.example.oren.menus.viewmodel;

//import com.example.oren.menus.data.AppDatabase;
import android.app.Application;

import com.example.oren.menus.data.Movie;
import com.example.oren.menus.data.MovieRepository;

//import java.util.List;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends AndroidViewModel {

    private LiveData<Movie> movie;

    private MovieRepository movieRepo;


    public MovieViewModel(Application application) {
        super(application);

        this.movieRepo = movieRepo;
        movieRepo = new MovieRepository(application);
    }

    public void init(long movieId) {
        if (this.movie != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        movie = movieRepo.getMovie(movieId);
    }
}