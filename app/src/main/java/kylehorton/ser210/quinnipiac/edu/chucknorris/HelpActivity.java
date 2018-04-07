package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the HelpActivity which displays the Help screen. This screen provides
 * instructions for how the user can use the app.
 *
 */
public class HelpActivity extends AppCompatActivity {
    private TextView header, desc;

    // creates view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        header = (TextView) findViewById(R.id.header);
        header.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));

        desc = (TextView) findViewById(R.id.desc);
        desc.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        desc.setMovementMethod(new ScrollingMovementMethod());
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
            Toast.makeText(HelpActivity.this, "There's no joke to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.search){
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.add_favorites) {
            Toast.makeText(HelpActivity.this, "No joke to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
