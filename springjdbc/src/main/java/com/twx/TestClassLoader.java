package com.twx;

import java.lang.reflect.Constructor;

/**
 * Created by twx on 2017/7/4.
 */
public class TestClassLoader {
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass("entity.Birthday");
            System.out.println(
                    aClass.newInstance().toString()
            );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
