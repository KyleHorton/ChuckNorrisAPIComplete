package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;
/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the settings activity. It allows users to change the app's theme and font styles.
 *
 */
public class SettingsActivity extends AppCompatActivity {
    private Button darkBTN, dayBTN, defaultBTN, bubblegumBTN, matchmakerBTN, defaultTXT;
    private FavoritesDatabase database;
    public static int currTheme = R.style.AppThemeLight;
    public static String currText = "roboto.ttf";
    private TextView settingsText, backText, fontText;

    // creates views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // allows for back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        database = new FavoritesDatabase(this);
        settingsText = (TextView) findViewById(R.id.settingsText);
        settingsText.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        backText = (TextView) findViewById(R.id.backText);
        backText.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        fontText = (TextView) findViewById(R.id.fontText);
        fontText.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        darkBTN = (Button) findViewById(R.id.darkBTN);
        defaultBTN = (Button) findViewById(R.id.defaultTheme);
        dayBTN = (Button) findViewById(R.id.dayBTN);
        bubblegumBTN = (Button) findViewById(R.id.bubblegumBTN);
        matchmakerBTN = (Button) findViewById(R.id.matchmakerBTN);
        defaultTXT = (Button) findViewById(R.id.defaultText);

        // what to do if clicked
        darkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTheme = R.style.AppThemeDark;
                recreate();
            }
        });

        // what to do if clicked
        defaultBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTheme = R.style.AppThemeLight;
                recreate();
            }
        });

        // what to do if clicked
        dayBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTheme = R.style.AppThemeDay;
                recreate();
            }
        });

        // what to do if clicked
        bubblegumBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            currText = "bubblegum.ttf";
            recreate();
            }
        });

        // what to do if clicked
        matchmakerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            currText = "matchmaker.ttf";
            recreate();
            }
        });

        // what to do if clicked
        defaultTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            currText = "roboto.ttf";
            recreate();
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
        if (item.getItemId() == R.id.search){
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
            finish();;
            return true;
        }
        if (item.getItemId() == R.id.favorites){
            database.populate();
            Intent intent3 = new Intent(this, FavoritesActivity.class);
            startActivity(intent3);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(SettingsActivity.this, "There's no joke to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.add_favorites) {
            Toast.makeText(SettingsActivity.this, "No joke to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
