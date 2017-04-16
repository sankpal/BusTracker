package com.example.nihit.mtrack;

import android.*;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            android.location.Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
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

        double lat=16.673027;
        double lng=74.222023;

        LatLng Hanuman = new LatLng(lat,lng );
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(Hanuman,1);
        mMap.addMarker(new MarkerOptions().position(Hanuman).title("Hanuman Nagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Hanuman));

        LatLng Shantinagar = new LatLng(16.657839,74.223969);
        mMap.addMarker(new MarkerOptions().position(Shantinagar).title("Shantinagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Powarcolony = new LatLng(16.661633,74.224435);
        mMap.addMarker(new MarkerOptions().position(Powarcolony).title("Powar colony").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Raigadcolony = new LatLng(16.663813,74.223787);
        mMap.addMarker(new MarkerOptions().position(Raigadcolony).title("Raigad colony").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Yogeshwaricolony = new LatLng(16.666629,74.223549);
        mMap.addMarker(new MarkerOptions().position(Yogeshwaricolony).title("Yogeshwari colony").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Jagtapnagar = new LatLng(16.668484,74.223298);
        mMap.addMarker(new MarkerOptions().position(Jagtapnagar).title("Jagtap nagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Apoorvnagar = new LatLng(16.670397,74.222705);
        mMap.addMarker(new MarkerOptions().position(Apoorvnagar).title("Apoorv nagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng ITI = new LatLng(16.676149,74.220816);
        mMap.addMarker(new MarkerOptions().position(ITI).title("ITI").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Sambhajinagar
                = new LatLng(16.678756,74.221306);
        mMap.addMarker(new MarkerOptions().position(Sambhajinagar
        ).title("Sambhaji nagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng racecoursenaka = new LatLng(16.681125,74.222313);
        mMap.addMarker(new MarkerOptions().position(racecoursenaka).title("Racecourse naka").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng SamratBakery = new LatLng(16.683261,74.223303);
        mMap.addMarker(new MarkerOptions().position(SamratBakery).title("Samrat Bakery").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Panyachakhajina = new LatLng(16.686117,74.223010);
        mMap.addMarker(new MarkerOptions().position(Panyachakhajina).title("Panyacha khajina").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Nathagole = new LatLng(16.687419,74.222630);
        mMap.addMarker(new MarkerOptions().position(Nathagole).title("Nathagole").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Padmaraje = new LatLng(16.689294,74.222479);
        mMap.addMarker(new MarkerOptions().position(Padmaraje).title("Padmaraje").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Kharicorner = new LatLng(16.692412,74.222745);
        mMap.addMarker(new MarkerOptions().position(Kharicorner).title("Khari corner").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng Mirajkartikti = new LatLng(16.692581,74.224890);
        mMap.addMarker(new MarkerOptions().position(Mirajkartikti).title("Mirajkar tikti").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng shahumaidan = new LatLng(16.693334,74.225969);
        mMap.addMarker(new MarkerOptions().position(shahumaidan).title("Shahu maidan").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng binduchowk = new LatLng(16.696108,74.226617);
        mMap.addMarker(new MarkerOptions().position(binduchowk).title("Bindu chowk").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng shivajichowk = new LatLng(16.697567,74.223986);
        mMap.addMarker(new MarkerOptions().position(shivajichowk).title("Shivaji chowk").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng lugdioal = new LatLng(16.698011,74.224811);
        mMap.addMarker(new MarkerOptions().position(lugdioal).title("Lugdi oal").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng maharanapratapchowk = new LatLng(16.699083,74.225825);
        mMap.addMarker(new MarkerOptions().position(maharanapratapchowk).title("Maharana pratap chowk").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng laxmipuriganpatimandir = new LatLng(16.700260,74.228312);
        mMap.addMarker(new MarkerOptions().position(laxmipuriganpatimandir).title("laxmipuri ganpati mandir").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng laxmipurilokmatoffice = new LatLng(16.701096,74.230288);
        mMap.addMarker(new MarkerOptions().position(laxmipurilokmatoffice).title("laxmipuri lokmat office").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng venuscorner = new LatLng(16.702659,74.233066);
        mMap.addMarker(new MarkerOptions().position(venuscorner).title("venus corner").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng railwaystation = new LatLng(16.703277,74.237634);
        mMap.addMarker(new MarkerOptions().position(railwaystation).title("railway station").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng CBS = new LatLng(16.704636,74.242401);
        mMap.addMarker(new MarkerOptions().position(CBS).title("CBS").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng TouristHotel = new LatLng(16.704856,74.244574);
        mMap.addMarker(new MarkerOptions().position(TouristHotel).title("Tourist Hotel").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng KavlaNaka = new LatLng(16.706417,74.247071);
        mMap.addMarker(new MarkerOptions().position(KavlaNaka).title("Kavla Naka").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng SadarBazar = new LatLng(16.710763,74.247922);
        mMap.addMarker(new MarkerOptions().position(SadarBazar).title("Sadar Bazar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng ShahuCollege = new LatLng(16.712188,74.251927);
        mMap.addMarker(new MarkerOptions().position(ShahuCollege).title("Shahu College").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng DYPatil = new LatLng(16.714873,74.254919);
        mMap.addMarker(new MarkerOptions().position(DYPatil).title("D.Y. Patil").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng BhosaleWadi = new LatLng(16.719121,74.254773);
        mMap.addMarker(new MarkerOptions().position(BhosaleWadi).title("Bhosale Wadi").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng Kadamwadi = new LatLng( 16.717906,74.257269);
        mMap.addMarker(new MarkerOptions().position(Kadamwadi).title("Kadamwadi").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng KhadichaGanpati = new LatLng(16.660056,74.241333);
        mMap.addMarker(new MarkerOptions().position(KhadichaGanpati).title("Khadicha Ganpati").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng RKNagar = new LatLng(16.662604,74.239828);
        mMap.addMarker(new MarkerOptions().position(RKNagar).title("R.K. Nagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
        LatLng SaiColony = new LatLng(16.662363,74.233556);
        mMap.addMarker(new MarkerOptions().position(SaiColony).title("Sai Colony").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng KIT = new LatLng(16.654439,74.268671);
        mMap.addMarker(new MarkerOptions().position(KIT).title("KIT").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng KaneriWadi = new LatLng(16.637626,74.279639);
        mMap.addMarker(new MarkerOptions().position(KaneriWadi).title("Kaneri Wadi Phata").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        LatLng UchgaonPhata = new LatLng(16.658165,74.267057);
        mMap.addMarker(new MarkerOptions().position(UchgaonPhata).title("Uchgaon Phata").icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));

        /* if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);
        }*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                requestPermissions(new String[]{

                        android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.INTERNET
                }, 10);
            }

            return;
        } else {
            mMap.setMyLocationEnabled(true);
        }

    }
}
