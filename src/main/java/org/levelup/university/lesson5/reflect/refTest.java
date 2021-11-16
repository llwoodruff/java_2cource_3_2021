package org.levelup.university.lesson5.reflect;

import java.lang.reflect.Field;

public class refTest {
    @FieldCustomVal
    public String myFieldWithDefaultVal;

    @FieldCustomVal(value = "My custom val")
    public String myFieldWithCustomVal;

    public static void main(String[] args) throws IllegalAccessException {

        refTest m = new refTest();
        //тут рефлексия
        for(Field field : refTest.class.getFields()) {
            FieldCustomVal fieldCustomVal = field.getAnnotation(FieldCustomVal.class);
            field.set(m, fieldCustomVal.value());
        }
        //конец рефлексии
        System.out.println(m.myFieldWithCustomVal);
        System.out.println(m.myFieldWithDefaultVal);
    }
}

