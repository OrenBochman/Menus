package com.example.oren.menus.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "MoviesDBHelper";

    public MovieDbHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Creating all the tables");
        String CREATE_USERS_TABLE =
                String.format(
                        "CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s INTEGER,%s INTEGER)",
                        MovieConstants.TABLE_NAME,
                        MovieConstants.MOVIE_ID_COLUMN,
                        MovieConstants.MOVIE_TITLE_COLUMN,
                        MovieConstants.MOVIE_BODY_COLUMN,
                        MovieConstants.MOVIE_CATEGORY_COLUMN,
                        MovieConstants.MOVIE_IMAGE_URI_COLUMN,
                        MovieConstants.MOVIE_RATING_COLUMN,
                        MovieConstants.MOVIE_WATCHED_COLUMN);
        try {
            db.execSQL(CREATE_USERS_TABLE );
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, "Create table exception: " +
                    ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old date");
        try {
            db.execSQL("DROP TABLE IF EXISTS " + MovieConstants.DATABASE_NAME);
            onCreate(db);
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, "Create table exception: " +
                    ex.getMessage());
        }
    }
}
