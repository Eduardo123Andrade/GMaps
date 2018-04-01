package br.com.novaroma.gmaps;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.novaroma.gmaps.entity.Address;
import br.com.novaroma.gmaps.entity.Client;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AssetManager assetManager;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private Geocoder geocoder;
//    private Directions

    private String TAG = "Tag";
    private String TAG_ERROR = "Erro";

    private List<Client> listClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        geocoder = new Geocoder(this, Locale.getDefault());

        listClient = new ArrayList<>();

        try {
            readFile();
            setValuesInList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setValuesInList() throws IOException {
        String line;
        Address address;
        Client client;

        while ((line = bufferedReader.readLine()) != null) {
            String name = line.substring(46, 86).replaceAll("^\\s+", "");
            String socialReason = line.substring(6, 46).replaceAll("^\\s+", "");

            address = newAddress(line);
            client = new Client(name, socialReason, address);

            listClient.add(client);
            Log.d(TAG, client.toString());
        }
    }

    private Address newAddress(String line) {
        String streat = line.substring(87, 126).replaceAll("^\\s+", "");
        String neighborhood = line.substring(127, 166).replaceAll("^\\s+", "");
        String city = line.substring(167, 206).replaceAll("^\\s+", "");
        String state = line.substring(206, 208).replaceAll("^\\s+", "");
        String postalCode = line.substring(208, 218).replaceAll("^\\s+", "");

        return new Address(streat, neighborhood, city, state, postalCode);
    }

    private void readFile() throws IOException {
        assetManager = getResources().getAssets();
        inputStream = assetManager.open("CL1237001001.txt");
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near recife, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            setMarks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.animateCamera(CameraUpdateFactory.zoomTo(11f));

        mMap.setTrafficEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
    }

    private void setMarks() throws IOException {
        double lat;
        double lng;

        for (Client c : listClient) {
            try {
                lat = geocoder.getFromLocationName(c.getAddress().toString(), 1).get(0).getLatitude();
                lng = geocoder.getFromLocationName(c.getAddress().toString(), 1).get(0).getLongitude();
                creatMark(lat, lng, c.getSocialReason());
            } catch (IndexOutOfBoundsException e) {
                Log.d(TAG_ERROR, c.getAddress().toString());
            }
        }

        lat = geocoder.getFromLocationName(listClient.get(0).getAddress().toString(), 1).get(0).getLatitude();
        lng = geocoder.getFromLocationName(listClient.get(0).getAddress().toString(), 1).get(0).getLongitude();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));

    }

    private void creatMark(double latitude, double longitude, String socialReason) {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(socialReason));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}