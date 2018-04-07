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
 * This Activity displays the credits of the app. It gives credit to the creator of the app and API.
 *
 */
public class CreditsActivity extends AppCompatActivity {
    private TextView credHeader, credDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        credHeader = (TextView) findViewById(R.id.credHeader);
        credHeader.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));

        credDesc = (TextView) findViewById(R.id.credDesc);
        credDesc.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        credDesc.setMovementMethod(new ScrollingMovementMethod());
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
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.favorites) {
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
        if (item.getItemId() == R.id.share) {
            Toast.makeText(CreditsActivity.this, "There's no joke to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.search) {
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.add_favorites) {
            Toast.makeText(CreditsActivity.this, "No joke to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}