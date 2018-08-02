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
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)

public class MovieDbHelperTest {

    final private String TAG = "MovieDbHelperTest";
    private MovieDao dao;
    private AppDatabase mTestDb;


    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<List<Movie>> listObserver;
    @Mock
    private Observer<Movie> movieObserver;

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
        MockitoAnnotations.initMocks(this);


        Context context = InstrumentationRegistry.getTargetContext();
        mTestDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = mTestDb.movieDao();
    }

    @After
    public void closeDb() {
        mTestDb.close();
    }

    @Test
    public void insert(){
        // given
        Movie movie  = new Movie( 123,123L,"IT","it is Bad","www.tmdb.com",7,1,"Horror");
        dao.findAllMovies().observeForever(listObserver);
        // when
        dao.insertMovie(movie);
        // then
        verify(listObserver).onChanged(Collections.singletonList(movie));
    }

    @Test
    public void writeMoviesAndReadInList() {
        // given
        List<Movie> moviesIn = DataGenerator.generateMovies();
        LiveData<List<Movie>> moviesOut = dao.findAllMovies();
        moviesOut.observeForever(listObserver);
        //when
        dao.insertMovie(moviesIn);
        //then
        assertThat(moviesIn.size(), equalTo(moviesOut.getValue().size()));
    }

    @Test
    public void updateItemTest() {
        //give
        //setup movies
        List<Movie> moviesIn = DataGenerator.generateMovies();
        //observe the movies
        LiveData<List<Movie>> moviesOut = dao.findAllMovies();
        moviesOut.observeForever(listObserver);

        //when
        dao.insertMovie(moviesIn);

        //and change an item
        Movie movie = moviesOut.getValue().get(0);
        Log.i(TAG, movie.toString());
        movie.setBody(movie.getBody() + " -- NOT");
        LiveData<Movie> movieOut = dao.findById(movie.getId());
        movieOut.observeForever(movieObserver);

        //update the db
        dao.updateMovie(movie);

        //get them movie by id
//        moviesOut = dao.findAllMovies();
        assertThat(moviesIn.size(), equalTo(moviesOut.getValue().size()));
        assertThat(movieOut.getValue().getBody(), endsWith(" -- NOT"));
        assertThat(movie, equalTo(movieOut.getValue()));
    }

    @Test
    public void deleteItemTest() {
        //given
        List<Movie> moviesIn = DataGenerator.generateMovies();

        //when
        dao.insertMovie(moviesIn);

        //get the movies back
        LiveData<List<Movie>> moviesOut = dao.findAllMovies();
        moviesOut.observeForever(listObserver);

        //change an item
        Movie movie = moviesOut.getValue().get(0);
        //update the db
        dao.deleteMovie(movie);

        //get them movie by id
        LiveData<Movie> movieOut = dao.findById(movie.getId());
        movieOut.observeForever(movieObserver);

        //then
        assertThat(movieOut.getValue(), is(nullValue()));
        assertThat(moviesOut.getValue().size(), equalTo(moviesIn.size() - 1));
    }
}