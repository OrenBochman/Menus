package com.example.oren.menus.data;

import android.content.Context;
//import com.example.android.persistence.AppExecutors;

import com.example.oren.menus.AppExecutors;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Movie.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase sInstance;
    private final  MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();


    @VisibleForTesting
    public static final String DATABASE_NAME = MovieConstants.DATABASE_NAME;

    public abstract MovieDao movieDao();


    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
//    public static AppDatabase getInstance(final Context context) {
//        if (sInstance == null) {
//            synchronized (AppDatabase.class) {
//                if (sInstance == null) {
//                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
//                            AppDatabase.class,MovieConstants.DATABASE_NAME).build();
// //                   sInstance.updateDatabaseCreated(context.getApplicationContext());
//                }
//            }
//        }
//        return sInstance;
//    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }


    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }


    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<Movie> movies = DataGenerator.generateMovies();


                            insertData(database, movies);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    private static void insertData(final AppDatabase database, final List<Movie> movies) {
        database.runInTransaction(() -> database.movieDao().insertMovies(movies));
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
