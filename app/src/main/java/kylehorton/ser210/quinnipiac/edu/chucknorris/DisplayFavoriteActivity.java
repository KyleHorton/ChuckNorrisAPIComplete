package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This Activity displays the individual favorite selected to delete.
 *
 */
public class DisplayFavoriteActivity extends AppCompatActivity {

    private Button delete;
    private TextView joke;
    private String lastJoke = "";
    private FavoritesDatabase database;
    private ShareActionProvider shareActionProvider;

    // creates views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = new FavoritesDatabase(this);
        joke = (TextView)findViewById(R.id.textJoke);
        joke.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        joke.setMovementMethod(new ScrollingMovementMethod());
        joke.setText(getIntent().getStringExtra("joke"));
        lastJoke = joke.getText().toString();
        delete = (Button) findViewById(R.id.delete);


        // allows for back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // restores the data
        if (savedInstanceState != null) {
            joke.setText(savedInstanceState.getString("joke"));
        }

        // what to do when delete button is clicked
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteJoke(lastJoke);
                database.populate();
                Intent intent = new Intent(DisplayFavoriteActivity.this, FavoritesActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    // saves data if orientation flips
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("joke", joke.getText().toString());
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
            setShareActionIntent("Check out this hilarious joke from Chuck Norris Jokes app: \n\n" + joke.getText().toString());

        }
        return super.onOptionsItemSelected(item);
    }
}
