package be.ehb.LoginMockup.ui.whereToWorkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.FragmentManager;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import be.ehb.Ehealth.R;

public class FindYourPlace extends AppCompatActivity implements LocationListener {
    private static final int PERMISSION_CALL_ID = 1234;

    private static final LatLng BRUXELLES = new LatLng(50.38503,4.3517);
    private LocationManager lm;

    private MapFragment mapFragment;
    private GoogleMap googleMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_place);
        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);



        Button btn = (Button) findViewById(R.id.btn_startFindPlace);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindYourPlace.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    //@SuppressWarnings("MissingPermission")
    protected void onResume() {
        //need permissions: check permissions: 2 permissions checked: location, corse_location
        super.onResume();
        checkPermissions();

    }*/
    private void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Als geen permission: vraag er aan
            //Async call ! show a popup but you "stay here"
            ActivityCompat.requestPermissions(this,new String[] {
                    //define with  constants
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_CALL_ID );
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //signature: type(gps), frequency:10000 , 0 , listener
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            //signature: type(passive), frequency:10000 , 0 , listener
            lm.requestLocationUpdates( LocationManager.PASSIVE_PROVIDER, 10000,0,this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            //signature: type(Network)
            lm.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 10000,0,this);
        }
        //load map only after permissions are checked
        loadMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == PERMISSION_CALL_ID){
            //if permisson are activated if should come here
            //otherwise if user has no permission and then activate them (or not), it will "be checked again" by callin checkPermissions again
            checkPermissions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(lm!= null){
            lm.removeUpdates(this);
        }
    }

    private void loadMap(){
        //async call: notified when everything is done: cal back(anonymous class(=>inner class!) based on interface)
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //set our map
                FindYourPlace.this.googleMap = googleMap;
               googleMap.moveCamera(CameraUpdateFactory.zoomBy(15));
               googleMap.moveCamera(CameraUpdateFactory.newLatLng(BRUXELLES));// 50.8503 4.05*/
                googleMap.setMyLocationEnabled(true);
                googleMap.addMarker(new MarkerOptions().position(new LatLng(50.8503, 4.05)).title("Basic fit"));
            }
        });
    }

    //-- calling methods from interface
    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {
        //loaction.
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Toast.makeText(this, "Location:"+ latitude+ "/" + longitude, Toast.LENGTH_SHORT).show();
       if (googleMap != null){
           LatLng position = new LatLng(latitude,longitude);
           googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
       }
    }
}
