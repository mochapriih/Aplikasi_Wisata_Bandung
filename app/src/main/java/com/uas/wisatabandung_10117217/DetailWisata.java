package com.uas.wisatabandung_10117217;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
public class DetailWisata extends AppCompatActivity implements
        OnMapReadyCallback, PermissionsListener {
    TextView txtjudul,txtalamat;
    ImageView img;
    private PermissionsManager permissionsManager;
    private MapView mapView;
    private MapboxMap map;
    private LocationEngine locationEngine;
    Point originPosition;
    private static final String RED_PIN_ICON_ID = "red-pin-icon-id";
    private LocationChangeListeningActivityLocationCallback callback =
            new LocationChangeListeningActivityLocationCallback(this);
    private Point destinationPosition;
    private static final long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private static final long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    private NavigationMapRoute navigationMapRoute;
    private static final String TAG = "detail wisata";
    private static final String SA = "wisata";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this,getString(R.string.mapbox_access_token));
        setContentView(R.layout.detailwisata);

        txtjudul = (TextView) findViewById(R.id.textjudul);
        txtalamat = (TextView) findViewById(R.id.textalamat);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("judul")&& getIntent().hasExtra("alamat")) {
            String judulw = getIntent().getStringExtra("judul");
            String alamatw = getIntent().getStringExtra("alamat");

            txtjudul.setText(judulw);
            txtalamat.setText(alamatw);
        }

    }
    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {

        map = mapboxMap;

        //originPosition = Point.fromLngLat(106.945625,-6.235975);
        final String judulw = getIntent().getStringExtra("judul");
        mapboxMap.setStyle(Style.MAPBOX_STREETS,
                new Style.OnStyleLoaded() {
                    @Override public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);

                        final LatLng mapTargetLatLng = mapboxMap.getCameraPosition().target;
                        double lat = getIntent().getDoubleExtra("lat",0);
                        double lng = getIntent().getDoubleExtra("long",0);
                        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                        symbolManager.create(new SymbolOptions()
                                .withLatLng(new LatLng(lat,lng))
                                .withIconImage(RED_PIN_ICON_ID)
                                .withTextField(judulw)
                                .withIconSize(2.0f));
                        destinationPosition = Point.fromLngLat(lng,lat);
                        //destinationPosition = Point.fromLngLat(107.565804,-6.920298);
                        originPosition = Point.fromLngLat(mapTargetLatLng.getLongitude(),mapTargetLatLng.getLatitude());
                        //originPosition = Point.fromLngLat(106.945625,-6.235975);
                        getRoute(originPosition,destinationPosition);


                    }
                });



    }

    private void getRoute(Point origin, Point destination){
        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null) {
                            Log.e(SA, "tidak ada rute,tolong check kembali rumah sakit tujuan");
                            return;
                        } else if (response.body().routes().size() == 0) {
                            Log.e(SA, "tidak ditemukan rute");
                            return;
                        }

                        DirectionsRoute currentRoute = response.body().routes().get(0);
                        if (navigationMapRoute != null) {
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, map);
                        }
                        navigationMapRoute.addRoute(currentRoute);



                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Log.e(TAG, "Error: "+t.getMessage());
                    }
                });
    }
    /*@Override
    public boolean onMapClick(@NonNull LatLng point) {
        double lat = getIntent().getDoubleExtra("lat",0);
        double lng = getIntent().getDoubleExtra("lng",0);
        destinationPosition = Point.fromLngLat(lng,lat);
        originPosition = Point.fromLngLat(oLocation.getLongitude(),oLocation.getLatitude());
        //originPosition = Point.fromLngLat(-6.953740,107.505432);
        getRoute(originPosition,destinationPosition);


        return false;
    }*/
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Get an instance of the component
            LocationComponent locationComponent = map.getLocationComponent();

            // Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.NORMAL);
            initLocationEngine();

        } else {
            permissionsManager = new PermissionsManager((PermissionsListener) this);
            permissionsManager.requestLocationPermissions(this);
        }
    }
    @SuppressLint("MissingPermission")
    private void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();
        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);

    }
    private class LocationChangeListeningActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<DetailWisata> activityWeakReference;

        LocationChangeListeningActivityLocationCallback(DetailWisata activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }



        @Override
        public void onSuccess(LocationEngineResult result) {
            DetailWisata activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();

                if (location == null) {

                    return;
                }

                // Create a Toast which displays the new location's coordinates


                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.map != null && result.getLastLocation() != null) {
                    activity.map.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                }
            }
        }

        @Override
        public void onFailure(@NonNull Exception exception) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            map.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);

                }
            });
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        } else {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            finish();
        }
    }
    private void setCameraPosition(Location location){
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()),13.0));
    }

    @Override
    @SuppressWarnings( {"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(locationEngine != null){
            locationEngine.removeLocationUpdates(callback);
        }
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
