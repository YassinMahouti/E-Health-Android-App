package be.ehb.LoginMockup.ui.whereToWorkout;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import be.ehb.Ehealth.R;
/* 50.849.890	4.377.340
50,9233176	4,4512295
51	4
50.845.800	4.383.790
51	4
51	5
51	4
*/

public class WhereToWorkout extends FragmentActivity implements OnMapReadyCallback {
    private static final LatLng BRUXELLES = new LatLng(50.8503,4.3517);
    private static final LatLng BASIC_HAREN = new LatLng( 50.8911812 , 4.4267414  );
    private static final LatLng BASIC_MEISER = new LatLng(  50.858910    , 4.405360 );
    private static final LatLng BASIC_KAAI = new LatLng(  50.8447791 , 4.3215876);
    private static final LatLng BASIC_EVERE = new LatLng(  50.849890 ,	4.377340 );
    private static final LatLng BASIC_VILVOORDE = new LatLng(   50.9233176	, 4.4512295    );
    private static final LatLng BASIC_SCHAERBEEK = new LatLng(50.8700836        , 4.3648233 );
    private static final LatLng BASIC_AMBIORIX = new LatLng(  50.845800	, 4.383790 );
    private static final LatLng BASIC_ZEMST = new LatLng(   50.9922357      ,4.472778  );
    private static final LatLng BASIC_WEZEMBEEK = new LatLng(   50.8409251      , 4.9545439  );
    private static final LatLng BASIC_OVERIJSEN = new LatLng(     50.7909912    , 4.4873041 );

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to_workout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_to_workout);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(BRUXELLES).title("Brussels City"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BRUXELLES, 15));
        MarkerOptions markerOptionsBasicFit = new MarkerOptions();
        markerOptionsBasicFit.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptionsBasicFit.position(BASIC_HAREN));
    }
}
