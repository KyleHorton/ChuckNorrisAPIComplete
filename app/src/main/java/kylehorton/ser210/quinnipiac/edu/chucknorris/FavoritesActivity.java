package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This Activity displays the ListFragment of the favorites. It allows the user to select
 * a specific favorite, or delete all of the favorites in the database.
 *
 */
public class FavoritesActivity extends AppCompatActivity implements FavoritesListFragment.Listener {

    private FavoritesDatabase database;
    private ListView listView;
    private Button deleteAll;
    private String lastJoke = "";

    // creates views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(android.R.id.list);
        database = new FavoritesDatabase(this);
        deleteAll = (Button) findViewById(R.id.deleteAll);

        // allows for back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // what to do if button clicked
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteAll();
                database.populate();
                recreate();
            }
        });

        //what to do if item clicked in list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastJoke = listView.getItemAtPosition(position).toString();
                Intent intent = new Intent(FavoritesActivity.this, DisplayFavoriteActivity.class);
                intent.putExtra("joke", lastJoke);
                startActivity(intent);
                finish();
            }
        });
    }

    // creates menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // tells you which item from the toolbar is clicked on
    // each item has a specific id
    // each item has a specific action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.search){
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
            finish();;
            return true;
        }
        if (item.getItemId() == R.id.settings){
            Intent intent3 = new Intent(this, SettingsActivity.class);
            startActivity(intent3);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(FavoritesActivity.this, "There's no joke to share!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // uses interface
    @Override
    public void itemClicked(int position) {
        lastJoke = listView.getItemAtPosition(position).toString();
        Intent intent = new Intent(FavoritesActivity.this, DisplayFavoriteActivity.class);
        intent.putExtra("joke", lastJoke);
        startActivity(intent);
        finish();

    }
}

