// IMyAidlInterface.aidl
package com.weatherfish.testparcalable;

// Declare any non-default types here with import statements
import com.weatherfish.testparcalable.MyParcel;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int testInt();

    String testString();

    List<String> testList(int n);

    MyParcel testParcel(inout MyParcel parcel);


}
