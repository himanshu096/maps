package ga.himanshu.home.teststripe;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ga.himanshu.home.teststripe.models.Location;
import ga.himanshu.home.teststripe.models.Result;
import ga.himanshu.home.teststripe.services.Callback;
import ga.himanshu.home.teststripe.services.MyPlaceService;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    float zoomLevel = (float) 16.0;
    private GoogleMap mMap;
    Button gotobtn;
    EditText place;


    MyScroll parentscroll;
    ArrayAdapter<String> adapter;
    ListView listview;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        place=(EditText)findViewById(R.id.place_text);
        gotobtn=(Button)findViewById(R.id.goto_btn);
        gotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listmyPlace(place.getText().toString());
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        parentscroll=(MyScroll) findViewById(R.id.mapsscroll);


        items = new ArrayList<String>();
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        items.add("dhkbkuds");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listview = (ListView) findViewById(R.id.mylistview);
        listview.setAdapter(adapter);
        listview.setScrollContainer(false);


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

        // Add a marker in Sydney and move the camera
        LatLng shikohabad = new LatLng(27.1062, 78.5882);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.addMarker(new MarkerOptions().position(shikohabad).title("you are in shikohabad himanshu").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shikohabad,zoomLevel));
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {


                return false;
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.d("codeo",""+latLng.latitude+","+latLng.longitude);
               // mMap.addMarker(new MarkerOptions().position(latLng).title("you are in somewhere himanshu").draggable(true));
                listmyPlace(latLng.latitude+","+latLng.longitude);



            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (parentscroll.isEnableScrolling()) {
                    parentscroll.setEnableScrolling(false);
                }else{
                    parentscroll.setEnableScrolling(true);
                }

            }
        });


    }

    public void listmyPlace(String address){
        MyPlaceService service=new MyPlaceService();
        service.listPlace(address, new Callback<Location>() {


            @Override
            public void onSuccess(Location response) {
                LatLng cplace=new LatLng(response.getResults().get(0).getGeometry().getLocation().getLat(),response.getResults().get(0).getGeometry().getLocation().getLng());
                Log.d("codeo",response.getResults().get(0).getGeometry().getLocation().getLat().toString()+","+response.getResults().get(0).getGeometry().getLocation().getLng().toString());
                Log.d("codeo",response.getResults().get(0).getFormattedAddress());
                mMap.addMarker(new MarkerOptions().position(cplace).anchor(0.0f, 1.0f).title("you are in"+response.getResults().get(0).getFormattedAddress()));

                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cplace,zoomLevel));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(cplace),2000,null);
            }

            @Override
            public void onFailure(Error error) {
                Log.d("codeo","FAIled");

            }
        });

    }

}
