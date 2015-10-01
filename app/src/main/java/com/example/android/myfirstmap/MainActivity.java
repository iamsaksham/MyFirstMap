package com.example.android.myfirstmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady=false;

    static final CameraPosition NOIDA = CameraPosition.builder()
            .target(new LatLng(28.628157, 77.367325))
            .zoom(14)
            .tilt(45)
            .build();

    static final CameraPosition DUBLIN = CameraPosition.builder()
            .target(new LatLng(53.3478, -6.2579))
            .zoom(14)
            .tilt(45)
            .build();

    static final CameraPosition TOKYO = CameraPosition.builder()
            .target(new LatLng(35.6895, 139.6917))
            .zoom(14)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNoida = (Button) findViewById(R.id.btnNoida);
        btnNoida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady)
                   flyTo(NOIDA);
            }
        });

        Button btnDublin = (Button) findViewById(R.id.btnDublin);
        btnDublin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    flyTo(DUBLIN);
            }
        });

        Button btnTokyo = (Button) findViewById(R.id.btnTokyo);
        btnTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(TOKYO);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap map){
        mapReady=true;
        m_map = map;
        //m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

       flyTo(NOIDA);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
    }

}
