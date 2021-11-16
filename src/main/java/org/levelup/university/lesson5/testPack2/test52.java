package org.levelup.university.lesson5.testPack2;

import org.levelup.university.reflect.ReflectionClass;

@ReflectionClass
public class test52 {
    String str;
    int cnt;

    test52() {
        str = "строка";
        cnt = 3;
    }

    @Override
    public String toString() {
        return "test52{" +
                "str='" + str + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
