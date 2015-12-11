package com.example.caelum.agendaalunos.listener;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by android5843 on 11/12/15.
 */
public class CustomLocationListener implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private final GoogleApiClient client;
    private final SupportMapFragment mapFragment;
    
    public CustomLocationListener(Context context, SupportMapFragment mapFragment) {
        this.client = new GoogleApiClient.Builder(context).addApi(LocationServices.API)
                            .addConnectionCallbacks(this).build();
        this.mapFragment = mapFragment;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = new LocationRequest();

        request.setInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(10);

        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        LocationServices.FusedLocationApi.removeLocationUpdates(client, this);

        this.client.disconnect();
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng coordinates = new LatLng(latitude, longitude);
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14));
    }

}
