package com.weatherfish.testparcalable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weatherfish on 15/11/20.
 */

public class MyParcel implements Parcelable {
    public int data;
    public String str;
    public List<String> list = new ArrayList<>();
    public Map<Integer,String> map = new HashMap<>();
    public byte[] bytes = new byte[10];

    public MyParcel(){
    }

    protected MyParcel(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        data = in.readInt();
        str = in.readString();
        in.readList(list, getClass().getClassLoader());
        in.readMap(map, getClass().getClassLoader());
        in.readByteArray(bytes);
    }

    public static final Creator<MyParcel> CREATOR = new Creator<MyParcel>() {
        @Override
        public MyParcel createFromParcel(Parcel in) {
            return new MyParcel(in);
        }

        @Override
        public MyParcel[] newArray(int size) {
            return new MyParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(data);
        dest.writeString(str);
        dest.writeList(list);
        dest.writeMap(map);
        dest.writeByteArray(bytes);
    }

    @Override
    public String toString() {
        return "MyParcel{" +
                "data=" + data +
                ", str='" + str + '\'' +
                ", list=" + list +
                ", map=" + map +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
