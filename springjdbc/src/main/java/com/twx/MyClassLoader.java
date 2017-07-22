package com.twx;

import java.io.*;

/**
 * Created by twx on 2017/7/4.
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("*****");
        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {
        System.out.println("oooo");
        String path = "E:\\workspace\\github\\Hibernate\\springjdbc\\" + name.replace(".", File.separator) + ".class";
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        InputStream is ;
        try {
            is = new FileInputStream(path);
            int c;
            int counter=0;
            while ((c = is.read()) != -1) {
                counter++;
//                System.out.print((char)c);
                if (counter > 2) {
                    byteSt.write(c);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteSt.toByteArray();
    }
}
