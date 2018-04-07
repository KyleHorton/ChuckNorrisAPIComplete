package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the home screen. From here a user can generate a joke, see the instructions, or see
 * the credits.
 *
 */
public class MainActivity extends AppCompatActivity {

    private String item;
    private ShareActionProvider shareActionProvider;
    private TextView mainTitle;
    private FavoritesDatabase database;
    private String jokeText = "";
    private Button generate, help, credits;

    // creates view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new FavoritesDatabase(this);
        mainTitle = (TextView) findViewById(R.id.mainTitle);
        mainTitle.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));

        generate = (Button) findViewById(R.id.generate);
        help = (Button) findViewById(R.id.help);
        credits = (Button) findViewById(R.id.credits);

        // what to do when clicked on
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomJokeAsync joke = new RandomJokeAsync();
                try {
                    jokeText = joke.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent  = new Intent(MainActivity.this, JokeActivity.class);
                intent.putExtra("text", jokeText);
                startActivity(intent);
                finish();

            }
        });

        // what to do when clicked on
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                finish();

            }
        });

        // what to do when clicked on
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);
                finish();


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
        if (item.getItemId() == R.id.search){
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
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
        if (item.getItemId() == R.id.add_favorites){
            Toast.makeText(MainActivity.this, "No joke to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(MainActivity.this, "There's no joke to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}


