package org.levelup.university.lesson5.reflect;

import org.levelup.university.configuration.DatabaseConfiguration;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Random;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class annotationTest {

    //public int reflInt1 = 110;

    @RandomInt(key = 1, key2 = 100)
    public Integer reflInt2;

    //@RandomInt(key = "Integer.MIN_VALUE", key2 = "Integer.MAX_VALUE")
    @RandomInt(key = 1, key2 = 100)
    public int reflInt3;

   /*
    @RandomInt(key = 1, key2 = 100)
    public String strString;
    */



    public static void main(String[] args) throws IllegalAccessException {

        Random random = new Random();
        annotationTest m = new annotationTest();
        //тут рефлексия
        for(Field field : annotationTest.class.getFields()) {
            RandomInt fieldCustomVal = field.getAnnotation(RandomInt.class);
            field.set(m, random.nextInt(fieldCustomVal.key2() - fieldCustomVal.key()) + fieldCustomVal.key());
        }
        //конец рефлексии
        System.out.println("int  " + m.reflInt3);
        System.out.println("Integer  " + m.reflInt2);

    }
}
