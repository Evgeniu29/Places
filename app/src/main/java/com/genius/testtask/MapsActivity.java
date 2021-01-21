package com.genius.testtask;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.bottomNav)
    BottomNavigationView bottomNav;

    private RequestQueue requestQueue;
    List<Place> places;
    PlaceAdapter adapter;
    private GoogleMap map;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        String email = getIntent().getExtras().getString("email");

        bottomNav.getMenu().getItem(0).setTitle(email);

        places = new ArrayList<>();

        jsonParse();

    }


    private void jsonParse() {
        requestQueue = Volley.newRequestQueue(this);
        String url = "https://2fjd9l3x1l.api.quickmocker.com/kyiv/places";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("places");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject item = jsonArray.getJSONObject(i);

                        Place place = new Place();

                        place.setId(item.getString("id"));

                        place.setName(item.getString("name"));

                        place.setLat(item.getString("lat"));

                        place.setLng(item.getString("lng"));

                        places.add(place);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new PlaceAdapter(getApplicationContext(), places);
                recyclerView.setAdapter(adapter);
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MapsActivity.this);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        for (int i = 0; i < places.size(); i++) {

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(places.get(i).getLat()), Double.parseDouble(places.get(i).getLng())))
                    .title(places.get(i).getName().toString()));

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(places.get(i).getLat()), Double.parseDouble(places.get(i).getLng())), 10));

        }


    }
}