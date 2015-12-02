package csci4448.cs.colorado.edu.whattowearweather;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by dan on 12/2/15.
 */
public class SettingsFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}
