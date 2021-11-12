package org.levelup.university.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;

public class JavaReflectionExamples {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        //
        Subject subject = new Subject("курлык", 1);

        // 1. Через объект
        Class<?> subjCLass = subject.getClass();
        //2. Через имя класса
        Class<?> subjectClass = Subject.class;

        Field[] fields = subjectClass.getDeclaredFields();

        for(Field field : fields){
            System.out.println(field.getType() + " "+ field.getName());
        }

        System.out.println("Constructors...");
        Constructor<?>[] constructors = subjectClass.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors){
            //Subject() -> []
            //Subject(String name) -> [Class<String>]
            //Subject(String name, int hours) -> [Class<String>, Class<int>]
            Class<?>[] types = constructor.getParameterTypes();
            System.out.println(Arrays.toString(types));
        }

        //subject(....)
        Field subjectNameField = subjectClass.getDeclaredField("name");
        subjectNameField.setAccessible(true); //для обхода приавтности, иначе бы упал этот блок.
        String subjectName = (String) subjectNameField.get(subject);
        System.out.println(subjectName);
        subjectNameField.set(subject, "ssddfg");
        System.out.println((String) subjectNameField.get(subject));

    }
}
