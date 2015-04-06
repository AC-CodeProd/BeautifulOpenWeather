package fr.ac.codeprod.beautifulopenweather.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import fr.ac.codeprod.beautifulopenweather.R;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}