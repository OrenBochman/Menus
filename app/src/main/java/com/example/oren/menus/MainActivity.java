package com.example.oren.menus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;

//import android.content.DialogInterface;
//import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import com.example.oren.menus.data.Movie;
import com.example.oren.menus.data.SampleData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.oren.menus.R.drawable.ic_film;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private ArrayList<String> list;
    private final ArrayList<Movie> movieData =new ArrayList<>();

    final String TAG="MoviesApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView;
        Button addMovieButton;
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

        addMovieButton = findViewById(R.id.addMovieButton);
        addMovieButton.setOnClickListener(view -> Snackbar.make(findViewById(android.R.id.content), "short click " ,Snackbar.LENGTH_SHORT).show());

        registerForContextMenu(addMovieButton);

        ///////////////  movieList   ///////////////////////////////////////////////////////////////

        listView =  findViewById(R.id.movieList);

            movieData.addAll(SampleData.getMovies());

        for (Movie movie:
                movieData) {
            Log.i(TAG, "onCreate: " + movie.toString());

        }

        //// bootstrap list with some sample data
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };


        list= new ArrayList<>();
        list.addAll(Arrays.asList(values));
        // the ListAdapter converts  String -> ListItem
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
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
    public void onCreateContextMenu(ContextMenu menu, View v,
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
        list.remove(id);
        adapter.notifyDataSetChanged();
        Snackbar.make(findViewById(android.R.id.content), "remove " + id ,Snackbar.LENGTH_SHORT).show();

    }

    private void editFromList(final int id)
    {
        String item = list.get(id);
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
            list.set(id ,YouEditTextValue);
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
        list.clear();
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
}