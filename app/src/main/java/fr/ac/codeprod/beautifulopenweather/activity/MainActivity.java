package fr.ac.codeprod.beautifulopenweather.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import fr.ac.codeprod.beautifulopenweather.fragment.NavigationDrawerFragment;
import fr.ac.codeprod.beautifulopenweather.R;
import fr.ac.codeprod.beautifulopenweather.fragment.CityDetailsFragment;
import fr.ac.codeprod.beautifulopenweather.preference.BeautifulOpenWeatherPreference;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Toolbar mToolbar;
    private BeautifulOpenWeatherPreference mBeautifulOpenWeatherPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBeautifulOpenWeatherPreference = new BeautifulOpenWeatherPreference(this);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mToolbar = ((Toolbar) findViewById(R.id.toolbar));
        mTitle = mToolbar.getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Log.v("ici", "onNavigationDrawerItemSelected => " + position);
        // update the main content by replacing fragments
        FragmentManager mFragmentManager = getSupportFragmentManager();
        CityDetailsFragment mCityDetailsFragment = CityDetailsFragment.newInstance("Paris,fr");
        mFragmentManager.beginTransaction()
                .replace(R.id.container, mCityDetailsFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        /*switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }*/
    }

    public void restoreActionBar() {
        mToolbar.setTitle(R.string.app_name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
