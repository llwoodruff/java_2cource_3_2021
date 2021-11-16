package org.levelup.university.lesson5.testPack2;

import org.levelup.university.reflect.ReflectionClass;

@ReflectionClass
public class test5 {
    String str;
    int cnt;

    test5() {
        str = "строка";
        cnt = 3;
    }

    /**
     * Переопределение метода toString
     **/
    @Override
    public String toString() {
        return "test5{" +
                "str='" + str + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
