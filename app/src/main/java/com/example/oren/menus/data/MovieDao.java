package com.example.oren.menus.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertMovies(Movie... movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<Movie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     long insertMovies(Movie movie);

    @Update
     int updateMovies(Movie... users);

    @Update
     int updateMovie(Movie movie);

    @Delete
     void deleteMovies(Movie... movies);

    @Delete
    void deleteMovie(Movie movies);


    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME + " order by movieTitle asc ")
     Movie[] findAllMovies();

    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME + " where movieId = :movieId ")
    Movie findById(long movieId);

    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME + " where movieTitle like :title order by movieTitle asc  " )
    Movie[] findByName(String title);

    @Query("SELECT * FROM " + MovieConstants.TABLE_NAME + " where movieCategory like :category order by movieTitle asc" )
    Movie[] findByCategory(String category);

}

