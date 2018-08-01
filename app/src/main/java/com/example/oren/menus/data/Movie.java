package com.example.oren.menus.data;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.example.oren.menus.data.MovieConstants.*;

@Entity(tableName = TABLE_NAME)
public class Movie
      //  implements Serializable
{

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = MOVIE_ID_COLUMN)
    private long _id;

    @ColumnInfo(name = MovieConstants.MOVIE_WEB_ID_COLUMN)                      //column in local DB
    @SerializedName(MovieConstants.TMDBM_WEB_ID_COLUMN)                         //id for API
    private long webId;

    @ColumnInfo(name = MOVIE_TITLE_COLUMN)                                      //id for local DB
    @SerializedName(MovieConstants.TMDBM_TITLE_COLUMN)                          //id for API
    private String title;

    @ColumnInfo(name = MOVIE_BODY_COLUMN)
    @SerializedName(MovieConstants.TMDB_BODY_COLUMN)                          //id for API
    private String body;

    @ColumnInfo(name = MOVIE_IMAGE_URI_COLUMN)
    @SerializedName(MovieConstants.TMDB_IMAGE_URI_COLUMN)                         //id for API
    private String uri;

    @ColumnInfo(name = MOVIE_RATING_COLUMN)
    @SerializedName(MovieConstants.TMDB_RATING_COLUMN)                         //id for API
    private int rating;

    @ColumnInfo(name = MOVIE_WATCHED_COLUMN)
    private int watched;

    @ColumnInfo(name = MOVIE_CATEGORY_COLUMN)
    private String category;

    @Ignore
    @SerializedName("poster_path")
    Bitmap picture; //picture from web

    @Ignore
    public Movie(int id, long webId, String title, String body, String uri, int rating, int watched, String category) {
        this._id = id;
        this.webId = webId;
        this.title = title;
        this.body = body;
        this.uri = uri;
        this.rating = rating;
        this.watched = watched;
        this.category = category;
    }
    @Ignore
    public Movie(String title, String body, String uri, int rating, int watched, String category) {
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
        return _id == movie._id &&
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
                "id=" + _id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", uri='" + uri + '\'' +
                ", rating=" + rating +
                ", watched=" + watched +
                ", category='" + category + '\'' +
                '}';
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public long getWebId() {
        return webId;
    }

    public void setWebId(long webId) {
        this.webId = webId;
    }
}
