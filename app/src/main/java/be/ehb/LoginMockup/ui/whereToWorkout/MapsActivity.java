package be.ehb.LoginMockup.ui.whereToWorkout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import be.ehb.Ehealth.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SeekBar.OnSeekBarChangeListener {
    private static final LatLng BRUXELLES = new LatLng(50.8503,4.3517);
    private static final int LOCATION_REQUEST = 500;
    GoogleMap mMap;
    SeekBar seekWidth, seekRed, seekGreen;
    Button btDraw, btClear;
    SupportMapFragment mapFragment;

    Polyline polyline =null ;
    ArrayList<LatLng> listPoints;
    ArrayList<LatLng> latLngList = new ArrayList<>();
    ArrayList<Marker> markerList = new ArrayList<>();

    int red =0,  green =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        seekWidth =(SeekBar) findViewById(R.id.sk_width_polyline);
        seekGreen =(SeekBar) findViewById(R.id.sk_green_polyline);
        seekRed =(SeekBar) findViewById(R.id.sk_red_polyline);
        btDraw =(Button) findViewById(R.id.bt_draw);
        btClear =(Button) findViewById(R.id.bt_clear_map);


        listPoints = new ArrayList<>();

        btDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // draw line on map with "short" click
                if(polyline !=null)  polyline.remove();
                PolylineOptions polylineOptions = new PolylineOptions()
                        .addAll(latLngList).clickable(true);
                polyline = mMap.addPolyline(polylineOptions);

                polyline.setColor(Color.rgb(red,green,0));

                setWidth();

            }
        });
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(polyline !=null)  polyline.remove();
                for (Marker marker : markerList)
                {
                    marker.remove();
                }
                //reset lists
                latLngList.clear();
                markerList.clear();
                //reset seeks
                seekWidth.setProgress(3);
                seekGreen.setProgress(0);
                seekRed.setProgress(0);
            }
        });


    }

    private void setWidth() {
        seekWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // get the seek progres (from width-seekbar)
                int width = seekWidth.getProgress();
                if(polyline != null) polyline.setWidth(width);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION} , LOCATION_REQUEST );
            googleMap.moveCamera(CameraUpdateFactory.zoomBy(15));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(BRUXELLES));
}

        // Add a marker in Bruxelles and move the camera
        //mMap.addMarker(new MarkerOptions().position(BRUXELLES).title("Marker in The best city ever"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BRUXELLES,15));
        mMap.setMyLocationEnabled(true);
        //addingCircleView(lLng);
        //addingCircleView(BRUXELLES);
        //om zelf punt in te geven door te klikken
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                Marker marker = mMap.addMarker(markerOptions);
                latLngList.add(latLng);
                markerList.add(marker);
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                //reste markers: "als er al punten zijn"
                if(listPoints.size() == 2) {
                    listPoints.clear();
                    mMap.clear();

                }
                listPoints.add(latLng);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                if(listPoints.size() ==1){
                    //voeg dan eerste marker toen aan de map: mbv BitmapDiscFact: ik gebruik hier de standaar markers van google(ik heb zelf al markers gemaakt)
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                } else {
                    //voeg tweede marker toe
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                //add marker to map
                mMap.addMarker(markerOptions);

                if(listPoints.size() ==2){
                    String url= getRequestUrl(listPoints.get(0),listPoints.get(1));
                    TaskRequestDirection taskRequestDirection = new TaskRequestDirection();
                    taskRequestDirection.execute(url);
                }

            }
        });
        // interactie met seeks for colors: SeekBarChangeListener !
        seekRed.setOnSeekBarChangeListener(this);
        seekGreen.setOnSeekBarChangeListener(this);

    }

    private String getRequestUrl(LatLng from, LatLng to) {
        // value of (from) adnd (to) in string
        String str_from = "origin=" + from.latitude + "," +from.longitude;
        String str_to = "destination=" + to.latitude + "," +to.longitude;
        // to set values enable the sensor
        String sensor = "sensor=false";
        // to find your direction: mode
        String mode ="mode=driving";
        // build all: concatenation
        String build =str_from +"&"+str_to +"&" +sensor +"&" +mode;
        // format the output(build) : 2 mogelijkheden: json of xml: ik doe hier json zodat ik later dit kan hergebruiken op web gedeelte
        String output = "json";
        // create the url request: google Place api
        String url ="https://maps.googleapis.com/maps/api/directions/" + output + "?" + build +"&key="+"AIzaSyDYR6gggyy0olNo9qtpRQr4byQP9ANR0cY"; // of oudere versie maps / api /directions /
        System.out.println("              "+url);
       // String tes_url ="https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=YOUR_API";
        return url;
    }

    private void addingCircleView(LatLng mLatlng){
        CircleOptions circleOptions = new CircleOptions()
                .center(mLatlng)
                .radius(30)
                .fillColor(Color.parseColor("#1AFB2323"))
                .strokeColor(Color.parseColor("#1AFB2323"))
                .strokeWidth(1);
        Circle myCircle1 = mMap.addCircle(circleOptions);
        circleOptions = new CircleOptions()
                .center(mLatlng)
                .radius(5)
                .fillColor(Color.parseColor("#0294FF"))
                .strokeColor(Color.parseColor("#0294FF"))
                .strokeWidth(1);
        Circle myCircle2 = mMap.addCircle(circleOptions);



    }
    /*
    @Override
    public void onLocationChanged(Location location) {
        //loaction.
        double latitude = location.getLatitude();
        latitude+=0.0001;
        double longitude = location.getLongitude();
        Toast.makeText(this, "Location:"+ latitude+ "/" + longitude, Toast.LENGTH_SHORT).show();
        if (mMap != null){
            LatLng position = new LatLng(latitude,longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
        addingCircleView(new LatLng(latitude,longitude));


    }*/


    private String requestDirection(String reqUrl){
        String str_response ="";
        InputStream inputStream = null;
        //connectie aanmaken mbv url: http
        HttpURLConnection httpURLConnection = null;
        try {
            //------------------------------------------------------------------------(
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //try to connect
            httpURLConnection.connect();
            //-------------------------------------------------------------------------
            // get response Stream
            inputStream =httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //need a buffer reader
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //------------------------------------------------------------------------(
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while((line = bufferedReader.readLine()) != null ){
                stringBuffer.append(line);
            }
            //------------------------------------------------------------------------(
            //buffer result bijhouden : in string
            str_response = stringBuffer.toString();
            //afsluiten buffers
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpURLConnection.disconnect();
        }
        return str_response;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case LOCATION_REQUEST: if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
            }
            break;
        }
    }
// generate widgets for interaction with seekbars => OnSeekBarChangeListener() => this
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId())
        {
            case R.id.sk_red_polyline:
                red = progress;
                fromUser = true;
                break;
            case R.id.sk_green_polyline:
                green = progress;
                fromUser = true;
                break;

        }
        // set the color
        polyline.setColor(Color.rgb(red, green, 0));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class TaskRequestDirection extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String str_response = "";
            str_response = requestDirection(strings[0]);
            return str_response;
        }
        //Generate onPost Excute: void method


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //parse the json
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>>
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {

                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            // get list routes and display in map

            ArrayList points = null;
            PolylineOptions polylineOptions = null;

            for(List<HashMap<String, String>> path : lists ){
                points = new ArrayList();
                polylineOptions = new PolylineOptions();
                for(HashMap<String, String> point : path){
                    double lat = Double.parseDouble((point.get("lat")));
                    double lon = Double.parseDouble((point.get("lon")));
                    Toast.makeText(MapsActivity.this, lat +","+lon, Toast.LENGTH_SHORT).show();

                    points.add(new LatLng(lat,lon));

                }
                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);

            }
            if(polylineOptions != null){
                mMap.addPolyline(polylineOptions);
            } else {
                Toast.makeText(MapsActivity.this, "Direction not found", Toast.LENGTH_SHORT).show();

            }

        }
    }
 }
