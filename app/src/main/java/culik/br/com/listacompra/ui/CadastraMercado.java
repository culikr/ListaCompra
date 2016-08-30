package culik.br.com.listacompra.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.MercadoDataSource;
import culik.br.com.listacompra.utils.model.Mercados;
import culik.br.com.listacompra.utils.service.RetrofitServiceProdutoSincrono;

public class CadastraMercado extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private MercadoDataSource mds;
    private double lat;
    private double lgn;
    private String nome;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Marker marker;
    private EditText edOrigem, edDestino, edNome;
    private Button btnProcura;
    private Button btnSalva, btnCancela;
    private LocationManager locationManager;
    private String provider;

    public static boolean getLatLong(JSONObject jsonObject, CadastraMercado f) {

        try {

            f.lgn = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            f.lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

        } catch (JSONException e) {
            return false;

        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_mercado);
        mds = new MercadoDataSource(this);


        mds.open();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        edNome = (EditText) findViewById(R.id.editText);
        edDestino = (EditText) findViewById(R.id.editText2);
        btnProcura = (Button) findViewById(R.id.btnProcura);
        btnSalva = (Button) findViewById(R.id.btnSalva);
        btnCancela = (Button) findViewById(R.id.btnCancela);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public View ProcuraLocal(View v) {
        RetrofitServiceProdutoSincrono r = new RetrofitServiceProdutoSincrono();
        final JSONObject jsonObject;
        try {
            JSONObject jo = r.procuraEndereco("Rua Lacy pereirsa figueiro 100, São Leopoldo");
            getLatLong(jo, this);

        } catch (Exception err) {
            Log.e("Erro", err.toString());
        }
        return v;
    }

    @Override
    public void onBackPressed() {
        cancelarEdicao();
        super.onBackPressed();
    }

    private void cancelarEdicao() {
        Toast.makeText(this, "Edicao cancelada!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelarEdicao(View v) {
        cancelarEdicao();
    }

    public void editarProduto(View v) {
        salvaDados();
    }

    private void salvaDados() {



/*
        Produto c = new Produto(te.getText().toString(), mer.getId(), Double.valueOf(te2.getText().toString()), te4.getText().toString());
        pr.insereProduto(c);
        */
        Mercados c = new Mercados(lat, lgn, edNome.getText().toString());
        mds.insereMercados(c);
        Intent i = new Intent();
        i.putExtra("lista", c);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = (location.getLatitude());
        lgn = (location.getLongitude());
        LatLng mLoc = new LatLng(lat,lgn);
        mMap.addMarker(new MarkerOptions().position(mLoc).title("Local Atual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mLoc));
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {


                    Toast.makeText(this, "Permissao negada", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }
}





