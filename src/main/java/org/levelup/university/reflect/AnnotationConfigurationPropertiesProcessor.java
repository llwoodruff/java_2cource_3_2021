package org.levelup.university.reflect;

import org.levelup.university.configuration.DatabaseConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotationConfigurationPropertiesProcessor {

    public static void processConfigurationFile(String filename){
        InputStream is = AnnotationConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is))){

            Map<String, String> configurationProperties = readProperties(fileReader);
            fillConfiguration(configurationProperties);
        } catch (IOException exc) {
            System.out.println("");
            throw new RuntimeException(exc);
        } catch (IllegalAccessException exc){
            System.out.println("Couldn't set property value to object field");
            throw new RuntimeException();
        }
    }
    //String(key) - property key
    //Objact(value) - property value
    private static Map<String, String> readProperties(BufferedReader reader) throws IOException{
        Map<String, String> properties = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null){
            if(!line.isBlank()){ //isBlank -> trim().isEmply()

                String[] elements = line.split("="); // split("=") -> String["database.password", "1234"] разделит на ключ и значение
                properties.put(
                        elements[0].trim(), //.replace("database", "").replace(".", ""),
                        elements[1].trim()
                );
            }
        }
        return properties;
    }

    private static void fillConfiguration(Map<String, String> properties) throws IllegalAccessException{
        //DatabaseConfiguration instance = DatabaseConfiguration.getInstance();

        Class<?> dbConfigurationClass = DatabaseConfiguration.class;

        Field[] fields = dbConfigurationClass.getDeclaredFields();
        for(Field field:fields){
            Property annotation = field.getAnnotation(Property.class);
            if (annotation != null) {
               String key = annotation.key();//получаем значение аннотации поля field
                String propertyValue = properties.get(key);

                field.setAccessible(true);
                field.set(DatabaseConfiguration.getInstance(), castStringToField(field.getType(), propertyValue)); //getInstance - сингелтон, те чтобы заполнялось только в одном месте

            }

            /*
            //реализация без аннотации
            if(!field.getName().equalsIgnoreCase("instance")) {
                String lowerCaseFieldName = field.getName().toLowerCase();
                Object propertyValue = properties.get(lowerCaseFieldName);

                field.setAccessible(true); //allow to set value to private field

                field.set(DatabaseConfiguration.getInstance(), castStringToField(field.getType(), propertyValue));
            }*/
        }

    }

    private static Object castStringToField(Class<?> fieldType, String propertyValue){
        if(fieldType == String.class){
            return propertyValue;
        }
        if(fieldType.isPrimitive() && fieldType != boolean.class){
            return propertyValue == null ? 0 : Integer.parseInt(propertyValue);
        }
        /*
        if(fieldType.isPrimitive() && fieldType != boolean.class){
            return Integer.parseInt((String) propertyValue);
        }
        */
        return null;
    }
}
