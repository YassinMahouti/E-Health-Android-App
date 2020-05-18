package be.ehb.LoginMockup.ui.corona;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.placefinder.MapsActivity;

public class FindAHospital extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    List<String> titel_en_adres = new ArrayList<>();
    List<LatLng> lat_long = new ArrayList<>();

    List<String> doc_adres_info = new ArrayList<>();
    List<LatLng> doc_lat_long = new ArrayList<>();
    Button btn_doctor;
    Button btn_hospital;
    boolean chose_one;
    private GoogleMap map;
        // om reeds gecodeerde methode, functie te kunnen hergebruiken uit MapsAct.
    MapsActivity activity = new MapsActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_hospital);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_hospital);
       mapFragment.getMapAsync((OnMapReadyCallback) this);
       //Lijst aanmaken om de markers op de kaart te plaatsen
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_1 + ConstantenVoorMaps.HOS_TIT_1);    lat_long.add(ConstantenVoorMaps.HOS_1);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_2 + ConstantenVoorMaps.HOS_TIT_2);    lat_long.add(ConstantenVoorMaps.HOS_2);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_3 +ConstantenVoorMaps.HOS_TIT_3);     lat_long.add(ConstantenVoorMaps.HOS_3);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_4 +ConstantenVoorMaps.HOS_TIT_4);     lat_long.add(ConstantenVoorMaps.HOS_4);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_5 +ConstantenVoorMaps.HOS_TIT_5);     lat_long.add(ConstantenVoorMaps.HOS_5);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_6 +ConstantenVoorMaps.HOS_TIT_6);     lat_long.add(ConstantenVoorMaps.HOS_6);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_7 +ConstantenVoorMaps.HOS_TIT_7);     lat_long.add(ConstantenVoorMaps.HOS_7);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_8 +ConstantenVoorMaps.HOS_TIT_8);     lat_long.add(ConstantenVoorMaps.HOS_8);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_9  +ConstantenVoorMaps.HOS_TIT_9);    lat_long.add(ConstantenVoorMaps.HOS_9);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_10+ConstantenVoorMaps.HOS_TIT_10);    lat_long.add(ConstantenVoorMaps.HOS_10);
       titel_en_adres.add(ConstantenVoorMaps.HOS_TIT_11 +ConstantenVoorMaps.HOS_TIT_11);   lat_long.add(ConstantenVoorMaps.HOS_11);

       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_1 + ConstantenVoorMaps.DOCTOR_ADR_1 + ConstantenVoorMaps.DOCTOR_GSM_1);
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_2 + ConstantenVoorMaps.DOCTOR_ADR_2 + ConstantenVoorMaps.DOCTOR_GSM_2);
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_3 + ConstantenVoorMaps.DOCTOR_ADR_3 + ConstantenVoorMaps.DOCTOR_GSM_3);
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_4 + ConstantenVoorMaps.DOCTOR_ADR_4 + ConstantenVoorMaps.DOCTOR_GSM_4);
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_5 + ConstantenVoorMaps.DOCTOR_ADR_5 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_6 + ConstantenVoorMaps.DOCTOR_ADR_6 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_7 + ConstantenVoorMaps.DOCTOR_ADR_7 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_8 + ConstantenVoorMaps.DOCTOR_ADR_8 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_9 + ConstantenVoorMaps.DOCTOR_ADR_9 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_10 + ConstantenVoorMaps.DOCTOR_ADR_10 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_11 + ConstantenVoorMaps.DOCTOR_ADR_11 );
       doc_adres_info.add(ConstantenVoorMaps.DOCTOR_NAME_12 + ConstantenVoorMaps.DOCTOR_ADR_12 );

        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_1);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_2);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_3);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_4);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_5);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_6);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_7);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_8);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_9);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_10);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_11);
        doc_lat_long.add(ConstantenVoorMaps.DOCTOR_12);

        btn_doctor =(Button) findViewById(R.id.btn_doctor);
        btn_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chose_one= true;
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                for( int i=0 ; i < doc_lat_long.size()  ; i++)
                {
                    markerOptions.position(doc_lat_long.get(i));
                    markerOptions.title(doc_adres_info.get(i));
                    map.addMarker(markerOptions);
                }
            }
        });

        btn_hospital = (Button) findViewById(R.id.btn_hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chose_one= false;
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                for( int i=0 ; i < lat_long.size()  ; i++)
                {
                    markerOptions.position(lat_long.get(i));
                    markerOptions.title(titel_en_adres.get(i));
                    map.addMarker(markerOptions);
                }

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Add a marker in Sydney and move the camera
        map.addMarker(new MarkerOptions().position(ConstantenVoorMaps.BRUXELLES).title("Brussels City"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ConstantenVoorMaps.BRUXELLES, 15));
        map.setMyLocationEnabled(true);

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if( chose_one)
                {
                    Toast.makeText(FindAHospital.this, "Selected Doctor is:" + marker.getTitle(), Toast.LENGTH_LONG ).show();
                }
                else  Toast.makeText(FindAHospital.this, "Selected Hospital is:" + marker.getTitle(), Toast.LENGTH_LONG ).show();
                return false;
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Toast.makeText(this, "Location:"+ latitude+ "/" + longitude, Toast.LENGTH_SHORT).show();
        if (map != null){
            LatLng position = new LatLng(latitude,longitude);
            map.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
        activity.addingCircleView(new LatLng(latitude,longitude));
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
