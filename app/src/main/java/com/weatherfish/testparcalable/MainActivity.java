package com.weatherfish.testparcalable;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TEST_PARCEL = "parcel";

    IMyAidlInterface mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MyParcel myParcel = new MyParcel();
                myParcel.data = 12;
                myParcel.str = "123";
                myParcel.list.add("a");
                myParcel.list.add("b");
                myParcel.list.add("c");
                myParcel.list.add("d");
                intent.putExtra(TEST_PARCEL, myParcel);
                startActivity(intent);
                break;

            case R.id.button2:
                bindService(new Intent(MainActivity.this, MyService.class), new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.e("TAG", "onServiceConnected");
                        mService = IMyAidlInterface.Stub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.e("TAG", "onServiceDisconnected");
                        mService = null;
                    }
                }, BIND_AUTO_CREATE);
                break;
            case R.id.button3:
                try {
                    mService.basicTypes(111, 22222222222222l, true, 333.333f, 44.44, "5555555555");
                    Log.e("----", mService.testInt() + "");
                    Log.e("----", mService.testList(12) + "");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("----", mService.testString());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    MyParcel parcel = new MyParcel();
                    parcel.data = 12;
                    parcel.str = "123";
                    parcel.list.add("a");
                    parcel.list.add("b");
                    parcel.list.add("c");
                    parcel.list.add("d");
                    Log.e("+++++", mService.testParcel(parcel).toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button4:
                ObjectOutputStream out = null;
                try {
                    MySerializable file = new MySerializable();
                    out = new ObjectOutputStream(
                            new FileOutputStream(getExternalCacheDir() + "result.obj"));
                    out.writeObject(file);
                    Log.e("Serializable", new File(getExternalCacheDir() + "result.obj").length() + ":1");
                    out.writeObject(file);
                    Log.e("Serializable", new File(getExternalCacheDir() + "result.obj").length() + ":2");
                    file.userName = "--aaaaaaa--";
                    file.age = 33333;
                    out.writeObject(file);
                    Log.e("Serializable", new File(getExternalCacheDir() + "result.obj").length() + ":3");
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;
        }
    }
}
