package fr.ac.codeprod.beautifulopenweather.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import fr.ac.codeprod.beautifulopenweather.R;
import fr.ac.codeprod.beautifulopenweather.fragment.SettingsFragment;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class PreferenceActivity extends ActionBarActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemePreference);
        setContentView(R.layout.activity_preference);
        mToolbar = ((Toolbar) findViewById(R.id.toolbar));
        mToolbar.setTitle(R.string.action_settings);
        getFragmentManager().beginTransaction()
                .replace(R.id.container_preference, new SettingsFragment())
                .commit();

    }
}