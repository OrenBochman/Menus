package com.example.oren.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addMovieButton;
    StableArrayAdapter adapter;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //////////////////addMoviebutton /////////////////////////////////////////////////////////////

        addMovieButton = findViewById(R.id.addMovieButton);

        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "short click " , Toast.LENGTH_LONG).show();
            }
        });

        registerForContextMenu(addMovieButton);

        ///////////////  movieList   /////////////////////////////////////////////////////////////////

        listView =  findViewById(R.id.movieList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "Long Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        //// bootstrap list with some values

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        list= new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
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


    public void removeFromList(int id)//,final  View view)
     {
        list.remove(id);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addDB:
                Toast.makeText(getApplicationContext(), "add Db",Toast.LENGTH_LONG).show();
                return true;
            case R.id.addInet:
                Toast.makeText(getApplicationContext(), "add from internet",Toast.LENGTH_LONG).show();

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
        try {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            position = info.position;
        }catch(Exception e){

        }

        switch (item.getItemId()) {
            case R.id.remove_list_item:
                Toast.makeText(getApplicationContext(), "remove " + position , Toast.LENGTH_LONG).show();
                removeFromList(position);
                return true;
            case R.id.edit_list_item:
                Toast.makeText(getApplicationContext(), "edit " + position, Toast.LENGTH_LONG).show();
                return true;
            case R.id.remove_button:
                Toast.makeText(getApplicationContext(), "remove button at " , Toast.LENGTH_LONG).show();
                return true;
            case R.id.edit_button:
                Toast.makeText(getApplicationContext(), "edit button",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    /**
     * from http://www.vogella.com/tutorials/AndroidListView/article.html
     */
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context,
                                  int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}