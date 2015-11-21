package com.weatherfish.testparcalable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MyParcel parcel = getIntent().getParcelableExtra(MainActivity.TEST_PARCEL);
        Log.e("test", parcel.toString());
    }
}
