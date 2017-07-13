package com.example.a15041867.p08_map;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button btnNorth,btnCentral,btnEast;
    private GoogleMap map;
    private Marker north,central,east;
    Spinner spnRegion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnRegion = (Spinner) findViewById(R.id.spinnerRegion);

        spnRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spnRegion.getSelectedItemPosition() == 1){
                    LatLng poi_North = new LatLng(1.454381, 103.831424);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North
                            ,15));
                }else if(spnRegion.getSelectedItemPosition() == 2){
                    LatLng poi_East = new LatLng(1.367149, 103.928021);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East
                            ,15));
                }else if(spnRegion.getSelectedItemPosition() == 3){
                    LatLng poi_Central = new LatLng(1.297802, 103.847441);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central
                            ,15));
                }else{
                    LatLng poi_Singapore = new LatLng(1.352083, 103.819836);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_Singapore = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,15));
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
                }
                LatLng poi_North = new LatLng(1.454381, 103.831424);
                north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));

                LatLng poi_Central = new LatLng(1.297802, 103.847441);
                central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.367149, 103.928021);
                east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(marker.equals(north)){
                            Toast.makeText(MainActivity.this,north.getTitle().toString(),Toast.LENGTH_SHORT).show();
                        }else if(marker.equals(east)){
                            Toast.makeText(MainActivity.this,east.getTitle().toString(),Toast.LENGTH_SHORT).show();
                        }else if(marker.equals(central)){
                            Toast.makeText(MainActivity.this,central.getTitle().toString(),Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });


            }
        });

        btnCentral = (Button)findViewById(R.id.btnCentral);
        btnEast = (Button)findViewById(R.id.btnEast);
        btnNorth = (Button)findViewById(R.id.btnNorth);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_North = new LatLng(1.454381, 103.831424);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North
                        ,15));

            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_East = new LatLng(1.367149, 103.928021);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East
                        ,15));

            }
        });

        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_Central = new LatLng(1.297802, 103.847441);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central
                        ,15));

            }
        });

    }
}
