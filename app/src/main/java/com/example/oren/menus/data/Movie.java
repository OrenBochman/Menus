package com.example.oren.menus.data;

import android.graphics.Bitmap;

import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.example.oren.menus.data.MovieConstants.*;

@Entity(tableName = TABLE_NAME)
public class Movie {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = MOVIE_ID_COLUMN)
    public int id;

    @ColumnInfo(name = MOVIE_TITLE_COLUMN)
    public String title;

    @ColumnInfo(name = MOVIE_BODY_COLUMN)
    public String body;

    @ColumnInfo(name = MOVIE_IMAGE_URI_COLUMN)
    public String uri;

    @ColumnInfo(name = MOVIE_RATING_COLUMN)
    public int rating;

    @ColumnInfo(name = MOVIE_WATCHED_COLUMN)
    public int watched;

    @ColumnInfo(name = MOVIE_CATEGORY_COLUMN)
    public String category;

    @Ignore
    Bitmap picture; //picture from web


    public Movie(int id, String title, String body, String uri, int rating, int watched, String category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.uri = uri;
        this.rating = rating;
        this.watched = watched;
        this.category = category;
    }

    public Movie(String title, String body, String uri, int rating, int watched, String category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.uri = uri;
        this.rating = rating;
        this.watched = watched;
        this.category = category;
    }

    public Movie() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                rating == movie.rating &&
                watched == movie.watched &&
                Objects.equals(title, movie.title) &&
                Objects.equals(body, movie.body) &&
                Objects.equals(uri, movie.uri) &&
                Objects.equals(category, movie.category) &&
                Objects.equals(picture, movie.picture);
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", uri='" + uri + '\'' +
                ", rating=" + rating +
                ", watched=" + watched +
                ", category='" + category + '\'' +
                '}';
    }
}
