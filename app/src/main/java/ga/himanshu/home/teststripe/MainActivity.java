package ga.himanshu.home.teststripe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ListView listview;
    ArrayList<String> items;
    Fragment fragm;

    MyScroll parentscroll;
    ListView childlist;
    String[] someting={"vdkvkjsdvkds","jvnadskjndsajk","kjnvdsakjnvuds","jhdsbihsdjds","sdhgvbshnjkdsnvj","dsjvnsdknidsa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i =new Intent(this,MapsActivity2.class);
        startActivity(i);

        parentscroll=(MyScroll) findViewById(R.id.parent_scroll);
        parentscroll.setEnableScrolling(false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);






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
        listview = (ListView) findViewById(R.id.list_view2);
        listview.setAdapter(adapter);

//        parentscroll.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                findViewById(R.id.list_view2).getParent()
//                        .requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        });

//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                findViewById(R.id.parent_scroll).getParent()
////                        .requestDisallowInterceptTouchEvent(false);
//                Log.d("codeo","fragment touch");
//
//
//                return false;
//            }
//        });


    }

}
