package com.example.oren.menus.data;

import android.content.Context;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)

public class MovieDbHelperTest {

    final private String TAG = "MovieDbHelperTest";
    private MovieDao mMovieDao;
    private AppDatabase mTestDb;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public TestName name= new TestName();

    @Rule
    public Timeout timeout = Timeout.millis(1000L);

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mTestDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mMovieDao = mTestDb.movieDao();
    }

    @After
    public void closeDb() {
        mTestDb.close();
    }

    @Test
    public void writeMoviesAndReadInList() {
        List<Movie> moviesIn = DataGenerator.generateMovies();
        mMovieDao.insertMovie(moviesIn);
        LiveData<List<Movie>> moviesOut = mMovieDao.findAllMovies();
        assertThat(moviesIn.size(), equalTo(moviesOut.getValue().size()));
    }

    @Test
    public void updateItemTest() {
        //setup movies in the data base
        List<Movie> moviesIn = DataGenerator.generateMovies();
        mMovieDao.insertMovie(moviesIn);

        //get the movies back
        LiveData<List<Movie>> moviesOut = mMovieDao.findAllMovies();
        //change an item
        Movie movie = moviesOut.getValue().get(0);
        Log.i(TAG, movie.toString());
        movie.setBody(movie.getBody() + " -- NOT");

        //update the db
        mMovieDao.updateMovie(movie);

        //get them movie by id
        LiveData<Movie> movieOut = mMovieDao.findById(movie.getId());
        moviesOut = mMovieDao.findAllMovies();
        assertThat(moviesIn.size(), equalTo(moviesOut.getValue().size()));
        assertThat(movieOut.getValue().getBody(), endsWith(" -- NOT"));
        assertThat(movie, equalTo(movieOut));
    }

    @Test
    public void deleteItemTest() {

        //setup movies in the data base
        List<Movie> moviesIn = DataGenerator.generateMovies();
        mMovieDao.insertMovie(moviesIn);

        //get the movies back
        LiveData<List<Movie>> moviesOut = mMovieDao.findAllMovies();

        //change an item
        Movie movie = moviesOut.getValue().get(0);
        Log.i(TAG, movie.toString());
        movie.setBody( movie.getBody() + " -- NOT");

        //update the db
        mMovieDao.deleteMovie(movie);

        //get them movie by id
        LiveData<Movie> movieOut = mMovieDao.findById(movie.getId());
        assertThat(movieOut, is(nullValue()));

        moviesOut = mMovieDao.findAllMovies();
        assertThat(moviesOut.getValue().size(), equalTo(moviesIn.size() - 1));
    }
}