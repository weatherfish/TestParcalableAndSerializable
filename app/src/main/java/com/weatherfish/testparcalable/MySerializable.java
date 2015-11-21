package com.weatherfish.testparcalable;

import android.util.Base64;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static java.io.ObjectInputStream.GetField;

/**
 * Created by weatherfish on 15/11/21.
 */

public class MySerializable implements Serializable {
    public String userName = "weatherfish";
    public String password ="password";
    public transient int noSerializable = 100;
    public int age = 12;
    public boolean flag = true;

    public void writeObject(ObjectOutputStream oos) {
        try {
            ObjectOutputStream.PutField putField = oos.putFields();
            putField.put("userName", userName);
            putField.put("password", Base64.encodeToString(password.getBytes(), Base64.DEFAULT));
            putField.put("noSerializable", noSerializable);
            putField.put("age", age);
            putField.put("flag", flag);
            oos.writeFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readObject(ObjectInputStream ois){
        try {
            GetField getField = ois.readFields();
            userName = (String) getField.get("userName","");
            password = String.valueOf(Base64.decode(getField.get("password", "").toString().getBytes(), Base64.DEFAULT));
            noSerializable = getField.get("noSerializable", 0);
            age = getField.get("age",1);
            flag = getField.get("flag",false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "MySerializable{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", noSerializable=" + noSerializable +
                ", age=" + age +
                ", flag=" + flag +
                '}';
    }

}
