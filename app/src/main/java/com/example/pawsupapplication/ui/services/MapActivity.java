package com.example.pawsupapplication.ui.services;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.pawsupapplication.R;

/*
This class is responsible for displaying the location of a given service on a google maps window.

@author Shu Sun
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*
       Displays map on Page.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        Address address;
        String myLocation = "27 King's College Cir, Toronto, ON M5S 1A1";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName(myLocation, 1);

            if(addresses.size() > 0){
                address = addresses.get(0);
                double longitude = address.getLongitude();
                double latitude = address.getLatitude();

                LatLng location = new LatLng(latitude, longitude);
                //drawCircle(location);
                //Adds pinpoint to map
                map.addMarker(new MarkerOptions().position(location).title(myLocation));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
      Draws circle around the location.
     */
    private void drawCircle(LatLng point){

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(30);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x30ff0000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        map.addCircle(circleOptions);

    }

    /*
     Returns to the previous page.
    */
    public void back(View v) {
        Intent i = new Intent(this, ServiceActivity.class);
        startActivity(i);
    }
}