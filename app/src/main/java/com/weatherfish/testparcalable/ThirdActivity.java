package com.weatherfish.testparcalable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getExternalCacheDir() + "result.obj"));
            MySerializable file1 = (MySerializable) ois.readObject();

            MySerializable file2 = (MySerializable) ois.readObject();
            MySerializable file3 = (MySerializable) ois.readObject();
            Log.e("res", file1.toString());
            Log.e("res", file2.toString());
            Log.e("res", file3.toString());
            ois.close();
            Log.e("equals", "1: "+ (file1 == file2));
            Log.e("equals", "2: "+ (file1 == file3));
            Log.e("equals", "3: "+ (file2 == file3));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
