package org.levelup.university.lesson5.reflect;

import java.lang.reflect.Field;
import java.util.Random;

public class annotationTest {
    //private static boolean str;

   // public int reflInt1 = 110;

    @RandomInt(key = 1, key2 = 100)
    public Integer reflInt2;

    //@RandomInt(key = "Integer.MIN_VALUE", key2 = "Integer.MAX_VALUE")
    @RandomInt(key = 1, key2 = 100)
    public int reflInt3;

    @RandomString(register = 1, length = 20)
    public String str;
   /*
    @RandomInt(key = 1, key2 = 100)
    public String strString;
    */



    public static void main(String[] args) throws IllegalAccessException {

        Random random = new Random();
        annotationTest m = new annotationTest();

        //String str = m.str;

        try {
            for (Field field : annotationTest.class.getFields()) {
                RandomInt fieldCustomVal = field.getAnnotation(RandomInt.class);
                field.set(m, random.nextInt(fieldCustomVal.key2() - fieldCustomVal.key()) + fieldCustomVal.key());
            }

        } catch (NullPointerException e) {

        }
        System.out.println("int  " + m.reflInt3);
        System.out.println("Integer  " + m.reflInt2);


        Random randomStr = new Random();
        //annotationTest m2 = new annotationTest();
        final String strDef = "abcdefghijklmnopqrstuvwxyz";;//fieldCustomVal.str();
        //тут рефлексия
        Field field1 = null;
        RandomString annotation = field1.getAnnotation(RandomString.class);
        field1.set(m, annotation.length());
        System.out.println(m.str);

        for(Field field : annotationTest.class.getFields()) {
            RandomString fieldCustomVal = field.getAnnotation(RandomString.class);
           // field.set(fieldCustomVal.length());
            /*if(fieldCustomVal.length() > 0) {

                //int lngt = fieldCustomVal.lngt();
                for(int i = 0; i < fieldCustomVal.length(); i++) {
                    field.set(m, strDef.charAt(randomStr.nextInt(fieldCustomVal.length())));
                }

            }*/
            //field.set(m2, random.nextInt(fieldCustomVal.key2() - fieldCustomVal.key()) + fieldCustomVal.key());
        }
        System.out.println(m.str);
/*
        final String alphabet = "0123456789ABCDE";
        final int N = alphabet.length();
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            System.out.print(alphabet.charAt(r.nextInt(N)));
        }
        */


    }


}
