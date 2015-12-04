package csci4448.cs.colorado.edu.whattowearweather;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by dan on 12/4/15.
 */
public class LocationFinder {

    private Location mLocation;
    private Context mContext;
    private String mProvider;


    public LocationFinder(Context context) {
        mContext = context;
    }

    public Location updateLocation() {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        mProvider = locationManager.getBestProvider(criteria, true);

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(mProvider, 0, 0, locationListener);

        mLocation = locationManager.getLastKnownLocation(mProvider);

        // Remove the listener you previously added
        locationManager.removeUpdates(locationListener);

        return mLocation;
    }

}
