package org.levelup.university.lesson5.reflect.implement;

import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.lesson5.reflect.RandomInt;


import java.lang.reflect.Field;
import java.util.Map;

public class RandomIntSet {
   /* private static void fillConfiguration(Fields field) throws IllegalAccessException{
        //DatabaseConfiguration instance = DatabaseConfiguration.getInstance();
        if (String.class.equals(field.getType())) {

        }
        if (String.class.equals(field.getType())) {
            RandomInt minLength = field.getAnnotation(RandomInt.class);

        Class<?> dbConfigurationClass = DatabaseConfiguration.class;

        Field[] fields = dbConfigurationClass.getDeclaredFields();
        for(Field field:fields){
            RandomInt annotation = field.getAnnotation(RandomInt.class);
            if (annotation != null) {
                int i = annotation.value();//получаем значение аннотации поля field
                String propertyValue = properties.get(i);

                field.setAccessible(true);
                field.set();

            }
        }

    }*/

    /*private static Object castStringToField(Class<?> fieldType, String propertyValue){
        if(fieldType == String.class){
            return propertyValue;
        }
        if(fieldType.isPrimitive() && fieldType != boolean.class){
            return propertyValue == null ? 0 : Integer.parseInt(propertyValue);
        }*/
        /*
        if(fieldType.isPrimitive() && fieldType != boolean.class){
            return Integer.parseInt((String) propertyValue);
        }
        */
       /* return null;
    }*/
}
