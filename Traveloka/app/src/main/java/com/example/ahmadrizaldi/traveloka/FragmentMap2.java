package com.example.ahmadrizaldi.traveloka;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMap2 extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    double lat, lang;

    public FragmentMap2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView)mView.findViewById(R.id.peta);

        if (mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        lat = -7.9456286;
        lang = 112.6170078;

        final Marker[] mark = new Marker[1];
        mark[0] = googleMap.addMarker(new MarkerOptions().position(new LatLng(-7.9456286,112.6170078)).title("Rumahku").snippet("Ini rumahku"));
        final CameraPosition[] lokasi = {CameraPosition.builder().target(new LatLng(-7.9456286, 112.6170078)).zoom(5).bearing(0).tilt(0).build()};
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(lokasi[0]));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mark[0].remove();
                mark[0] = googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("Rumahku").snippet("Ini rumahku"));
//

                lat = latLng.latitude;
                lang = latLng.longitude;

            }
        });


    }
}
