package com.lucascalderon1.combustivel.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.lucascalderon1.combustivel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class PostoActivity extends AppCompatActivity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);

        webView = (WebView) findViewById(R.id.webv);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://google.com/maps/search/?api=1&querye");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

//    public void buscarInformacoesGPS(View view) {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(PostoActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            ActivityCompat.requestPermissions(PostoActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//            ActivityCompat.requestPermissions(PostoActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
//            return;
//        }
//
//        LocationManager mLockManager = (LocationManager) getSystemService(PostoActivity.this.LOCATION_SERVICE);
//        LocationListener mLockListener = new MinhaLocalizacaoListener();
//
//        mLockManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLockListener);
//
//        if (mLockManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            String texto = "latitude: " + MinhaLocalizacaoListener.latitude + " \n" +
//                    "longitude: " + MinhaLocalizacaoListener.longitude + "\n";
//            Toast.makeText(PostoActivity.this, texto, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(PostoActivity.this, "Localização desabilitada.", Toast.LENGTH_SHORT).show();
//        }
//
//        this.mostrarGoogleMaps(MinhaLocalizacaoListener.latitude, MinhaLocalizacaoListener.longitude);
//
//    }
//}
//
////        public void mostrarGoogleMaps(double latitude, double longitude) {
////            WebView wv = findViewById(R.id.webv);
////            wv.getSettings().setJavaScriptEnabled(true);
////            wv.loadUrl("https://google.com/maps/search/?api=1&querye" + latitude + "," + longitude);
////        }
////    }

