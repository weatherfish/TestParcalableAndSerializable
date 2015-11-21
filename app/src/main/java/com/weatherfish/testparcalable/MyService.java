package com.weatherfish.testparcalable;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.e("TAG", aString + anInt + aLong + aBoolean + aFloat + aDouble);
        }

        @Override
        public int testInt() throws RemoteException {
            return 10;
        }

        @Override
        public String testString() throws RemoteException {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "just do it";
        }

        @Override
        public List<String> testList(int n) throws RemoteException {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add("test" + i);
            }
            return list;
        }

        @Override
        public MyParcel testParcel(MyParcel parcel) throws RemoteException {
            Log.e("TAG",parcel.toString());
            parcel.data = 9999;
            parcel.str+="-----";
            parcel.map.clear();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                list.add("result" + i);
            }
            parcel.list.addAll(list);
            return parcel;
        }
    }
}
