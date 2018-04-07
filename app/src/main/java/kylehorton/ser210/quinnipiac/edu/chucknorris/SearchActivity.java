package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;
/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the SearchActivity, which allows users to search for a specific topic.
 * It will access the Async class after.
 *
 */
public class SearchActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;
    public static String topic = "";
    private String jokeText = "";
    private TextView searchTopic;
    private FavoritesDatabase database;

    // creates view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // allows for back navigation
        if (getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        database = new FavoritesDatabase(this);
        searchTopic = (TextView) findViewById(R.id.searchTopic);
        searchTopic.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        Button button = (Button) findViewById(R.id.generate);

        // what to do if clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText search = (EditText)findViewById(R.id.editText);
                topic = search.getText().toString();

                // checks the text string
                if (!topic.equals("")){
                    SearchJokeAsync async = new SearchJokeAsync();
                    try {
                        jokeText = async.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(SearchActivity.this, JokeActivity.class);
                    intent.putExtra("text", jokeText);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SearchActivity.this, "Please enter a topic!", // if no topic is entered but button still pressed
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // adds menu
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
        if (item.getItemId() == R.id.favorites){
            database.populate();
            Intent intent3 = new Intent(this, FavoritesActivity.class);
            startActivity(intent3);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.settings){
            Intent intent4 = new Intent(this, SettingsActivity.class);
            startActivity(intent4);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(SearchActivity.this, "There's no joke to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.add_favorites) {
            Toast.makeText(SearchActivity.this, "No joke to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
