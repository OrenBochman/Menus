package com.example.oren.menus.data;

import android.database.Cursor;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie... movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(List<Movie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

 //   @Update
 //   int updateMovies(Movie... users);

    @Update
    int updateMovie(Movie movie);

  //  @Delete
  //  void deleteMovies(Movie... movies);

    @Delete
    void deleteMovie(Movie movies);

    //@Query("SELECT * FROM comments where productId = :productId")
    //LiveData<List<Movie>> loadMovies(int movieId);

    //@Query("SELECT * FROM comments where productId = :productId")
    //List<Movie> loadMoviesSync(int movieId);

    //@Query("SELECT COUNT(*) FROM users WHERE _Id == :movieId AND last_update >= :timeout")
    //int hasMovie(int movieId, long timeout);

    @Query("SELECT COUNT(*) FROM "+ MovieConstants.TABLE_NAME +" WHERE _Id == :movieId")
    int hasMovie(long   movieId);

    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME
            + " ORDER BY " + MovieConstants.MOVIE_TITLE_COLUMN + " ASC ")
    LiveData<List<Movie>> findAllMovies();
    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME
            + " where _id = :id")
    LiveData<Movie> findById(long id);
//
//    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME
//            + " WHERE " + MovieConstants.MOVIE_TITLE_COLUMN
//            + " LIKE :movieTitle "
//            + " ORDER BY " + MovieConstants.MOVIE_TITLE_COLUMN + "  ASC")
//    LiveData<Movie> findByName(String movieTitle);

//    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME
//            + " WHERE " + MovieConstants.MOVIE_CATEGORY_COLUMN
//            + " LIKE :movieCategory "
//            + " ORDER BY  " + MovieConstants.MOVIE_TITLE_COLUMN + " ASC")
//    LiveData<Movie> findByCategory(String movieCategory);

    //@Query("SELECT * FROM "+ MovieConstants.TABLE_NAME + " order by movieTitle asc")
    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME)
    Cursor getCursorAll();

    @Query("SELECT COUNT(*) FROM " + MovieConstants.TABLE_NAME)
    int getCount();

}

