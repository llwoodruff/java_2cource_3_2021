package org.levelup.university.lesson5.testPack;

import org.hibernate.boot.archive.scan.spi.ScanResult;
import org.levelup.university.lesson5.testPack3.test53;
import org.levelup.university.reflection.Subject;

import java.lang.reflect.Field;

public class repository {

    public String[] processAnnotation(String packageName){

        Class<?> subjectClass = Subject.class;

        Field[] fields = subjectClass.getDeclaredFields();

        for(Field field : fields){
            System.out.println(field.getType() + " "+ field.getName());
        }

        String className = null;
        String formatClass = String.format("Значение переменной float = " +
                "%f, пока значение integer " +
                "переменная = %d, и string " +
                "= %s", className);
        //System.out.println(formatClass);
        String[] pacClass = new String[5]; //Количество найденных пакетов
        //здесь должен быть уикл по вставке пакетов в массив
        return pacClass;
    }

}
