package com.example.oren.menus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;
//import androidx.core.content.ContextCompat;

//import android.content.DialogInterface;
//import android.os.Build;
//import android.database.sqlite.SQLiteDatabase;
//import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
//import android.widget.Toolbar;

import com.example.oren.menus.data.AppDatabase;
//import com.example.oren.menus.data.MovieConstants;
import com.example.oren.menus.data.MovieCursorAdapter;
import com.example.oren.menus.data.SampleData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
//import java.util.Arrays;

//import javax.inject.Inject;

import static com.example.oren.menus.R.drawable.ic_film;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getName();

    //@Inject MyTwitterApiClient mTwitterApiClient;
    //@Inject SharedPreferences sharedPreferences;

    private ArrayAdapter adapter;
    private ArrayList<String> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ViewModelProvider.of(this).get(MovieViewModel::class.java);
        ////////////////// ActionBar //////////////////////////////////////////////////////////////
        ListView listView;

        //////////////////
        ActionBar actionBar = getSupportActionBar();
        if( actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(ic_film);
            //actionBar.setLogo(R.drawable.logo);
            actionBar.setTitle(R.string.title);
        }

        //////////////////add Movie Button /////////////////////////////////////////////////////////
        Button addMovieButton = findViewById(R.id.addMovieButton);
        addMovieButton.setOnClickListener(view -> Snackbar.make(findViewById(android.R.id.content), "short click " ,Snackbar.LENGTH_SHORT).show());

        registerForContextMenu(addMovieButton);

        ///////////////  movieList   ///////////////////////////////////////////////////////////////

        if(AppDatabase.getInstance(this).movieDao().getCount()==0)
            AppDatabase.getInstance(this).movieDao().insertMovie(SampleData.getMovies());

        // Find ListView to populate
        listView = findViewById(R.id.movieList);
        // Setup cursor adapter using cursor from last step
        MovieCursorAdapter movieAdapter = new MovieCursorAdapter(this, AppDatabase.getInstance(this).movieDao().getCursorAll());
        // Attach cursor adapter to the ListView
        listView.setAdapter(movieAdapter);

        registerForContextMenu(listView);
        listView.setOnItemClickListener((parent, view, position, id) -> Snackbar.make(findViewById(android.R.id.content), "Click ListItem Number " + position ,Snackbar.LENGTH_SHORT).show());
        listView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Snackbar.make(findViewById(android.R.id.content), "Long Click ListItem Number " + position ,Snackbar.LENGTH_SHORT).show();
            return false;
        });
    }

    ///////////// options menu /////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.addMovieButton) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.button_context_menu, menu);
        } else if(v.getId() == R.id.movieList) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_context_menu, menu);
        }
    }

    private void removeFromList(int id)//,final  View view)
    {
        movieList.remove(id);
        adapter.notifyDataSetChanged();
        Snackbar.make(findViewById(android.R.id.content), "remove " + id ,Snackbar.LENGTH_SHORT).show();

    }

    private void editFromList(final int id)
    {
        String item = movieList.get(id);
        //edit item via dialog
       // Intent myIntent1 = new Intent(this, Create.class);
       // startActivityForResult(myIntent1, 0);
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.show();
        final EditText editText = new EditText(this);

        alert.setMessage("Enter Your Message");
        alert.setTitle("Enter Your Title");
        editText.setText(item);
        alert.setView(editText);
        alert.setPositiveButton("Ok", (dialog, whichButton) -> {
            String YouEditTextValue = editText.getText().toString();
            movieList.set(id ,YouEditTextValue);
            adapter.notifyDataSetChanged();
            Snackbar.make(findViewById(android.R.id.content), "edit " + id + " success",Snackbar.LENGTH_SHORT).show();

        });

        alert.setNegativeButton("Cancel", (dialog, whichButton) -> Snackbar.make(findViewById(android.R.id.content), "edit " + id + " canceled",Snackbar.LENGTH_SHORT).show());

        alert.show();
    }


    private void addDbOptionHandler(){
        //TODO: send intent
        Snackbar.make(findViewById(android.R.id.content), "add from Db",Snackbar.LENGTH_SHORT).show();
    }

    private void addWebOptionHandler(){
        //TODO: send intent
        Snackbar.make(findViewById(android.R.id.content), "add from web",Snackbar.LENGTH_SHORT).show();
    }

    private void exitOptionHandler(){
        Snackbar.make(findViewById(android.R.id.content), "exit",Snackbar.LENGTH_SHORT).show();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }

    private void removeAllOptionHandler(){
        movieList.clear();
        adapter.notifyDataSetChanged();
        Snackbar.make(findViewById(android.R.id.content), "Removed all items",Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addDB:
                addDbOptionHandler();
                return true;
            case R.id.addWeb:
                addWebOptionHandler();
                return true;
            case R.id.exit:
                exitOptionHandler();
                return true;
            case R.id.removeAll:
                removeAllOptionHandler();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ///////////// context menu /////////////////////////////////////////////////////////////////////

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position=0;
        // AdapterView.AdapterContextMenuInfo info = item.getMenuInfo();
        //noinspection EmptyCatchBlock
        try {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            position = info.position;
        }catch(Exception e){ }

        switch (item.getItemId()) {
            case R.id.remove_list_item:
                removeFromList(position);
                return true;
            case R.id.edit_list_item:
                editFromList(position);
                return true;
            case R.id.remove_button:
                Snackbar.make(findViewById(android.R.id.content), "remove button",Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.edit_button:
                Snackbar.make(findViewById(android.R.id.content), "edit button",Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}