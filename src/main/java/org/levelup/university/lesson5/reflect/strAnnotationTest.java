package org.levelup.university.lesson5.reflect;

import java.lang.reflect.Field;
import java.util.Random;

public class strAnnotationTest {

    @RandomString(register = 1, length = 20)
    public int str;

    public static void main(String[] args) throws IllegalAccessException {
        Random randomStr = new Random();
        annotationTest m = new annotationTest();
        final String strDef = "abcdefghijklmnopqrstuvwxyz";;//fieldCustomVal.str();

        Random random = new Random();

        //String str = m.str;

        try {
            for (Field field : annotationTest.class.getFields()) {
                RandomString fieldCustomVal = field.getAnnotation(RandomString.class);
                field.set(m, random.nextInt(fieldCustomVal.length() - fieldCustomVal.register()) + fieldCustomVal.register());
            }

        } catch (NullPointerException e) {

        }
        System.out.println("int  " + m.str);

    }
}
