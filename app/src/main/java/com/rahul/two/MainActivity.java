package com.rahul.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rahul.two.Adapter.DetailAdapter;
import com.rahul.two.Database.AppDatabase;
import com.rahul.two.Database.Details;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mFrom;
    private EditText mTo;
    private Button mCalculate;
    private TextView mDistance;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private String mLatitude1, mLongitude1, mLatitude2,mLongitude2,
        mAddress1,mAddress2,mCity1,mCity2,mCounty1,mCountry2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrom = findViewById(R.id.etFrom);
        mTo  = findViewById(R.id.etTo);
        mDistance = findViewById(R.id.tvDistance);
        mRecyclerView = findViewById(R.id.recycler_view);

        callView();

        mFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("flag","2");
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
            }
        });


        mTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("flag","3");
                startActivityForResult(intent, 3);// Activity is started with requestCode 2
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            mAddress1=data.getStringExtra("Address");
            mCity1 = data.getStringExtra("City");
            mCounty1 = data.getStringExtra("Country");
            mLatitude1 = data.getStringExtra("Latitude");
            mLongitude1 = data.getStringExtra("Longitude");
            mFrom.setText(mAddress1+", "+mCity1+", "+mCounty1);
        }
        else if (requestCode ==3){
            mAddress2=data.getStringExtra("Address");
            mCity2 = data.getStringExtra("City");
            mCountry2= data.getStringExtra("Country");
            mLatitude2 = data.getStringExtra("Latitude");
            mLongitude2 = data.getStringExtra("Longitude");
            mTo.setText(mAddress2+", "+mCity2+", "+mCountry2);
        }
        else {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }
    }

    public void calculate(View view) {
        Double lat1 = Double.parseDouble(mLatitude1);
        Double long1 = Double.parseDouble(mLongitude1);
        Double lat2 = Double.parseDouble(mLatitude2);
        Double long2 = Double.parseDouble(mLongitude2);
        Double results = distance(lat1,long1,lat2,long2);
        Toast.makeText(getApplicationContext(),"Distance is "+results.toString()+"km",Toast.LENGTH_LONG).show();
        mDistance.setText(results.toString()+"km");
    }

    public void save(View view){

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();

        db.detailsDao().insertAll(new Details(mAddress1+","+mCity1+","+mCounty1,mAddress2+","+mCity2+","+mCountry2,mDistance.getText().toString()));
        callView();

    }

    private void callView() {

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();
        List<Details> users = db.detailsDao().getAllDetails();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DetailAdapter(users);
        mRecyclerView.setAdapter(mAdapter);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
