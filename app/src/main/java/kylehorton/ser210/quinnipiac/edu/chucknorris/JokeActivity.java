package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class displays the joke activity.  It displays the generated joke and allows for the user go back to the home screen,
 * share the joke or add it to the favorites.
 */
public class JokeActivity extends AppCompatActivity {
    private TextView text;
    private ShareActionProvider shareActionProvider;
    private FavoritesDatabase database;;

    // creates views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        text = (TextView) findViewById(R.id.joke);
        text.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        text.setMovementMethod(new ScrollingMovementMethod());
        text.setText(getIntent().getStringExtra("text"));
        database = new FavoritesDatabase(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        // restores the data
        if (savedInstanceState != null) {
            text.setText(savedInstanceState.getString("joke"));
        }
    }

    // saves data if orientation flips
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("joke", text.getText().toString());
    }

    // adds the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return super.onCreateOptionsMenu(menu);

    }

    // sends the joke using an intent
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }


    // tells you which item from the toolbar is clicked on
    // each item has a specific id
    // each item has a specific action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.search) {
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.favorites) {
            database.populate();
            Intent intent3 = new Intent(this, FavoritesActivity.class);
            startActivity(intent3);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.settings) {
            Intent intent4 = new Intent(this, SettingsActivity.class);
            startActivity(intent4);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.add_favorites){
            addData(text.getText().toString());

        }
        if (item.getItemId() == R.id.share){
            setShareActionIntent("Check out this hilarious joke from Chuck Norris Jokes app: \n\n" + text.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    // adds data to the database and notifies the user if successful
    public void addData(String item){
        boolean dataInserted = database.addData(item);

        if (dataInserted){
            Toast.makeText(JokeActivity.this, "Joke added to favorites!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(JokeActivity.this, "Oops! Something went wrong!",
                    Toast.LENGTH_LONG).show();
        }
    }
    // bring the user back home
    public void onHome(View view) {
        Intent intent = new Intent(JokeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
