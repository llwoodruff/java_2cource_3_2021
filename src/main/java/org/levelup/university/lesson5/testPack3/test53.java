package org.levelup.university.lesson5.testPack3;

import org.levelup.university.reflect.ReflectionClass;

@ReflectionClass
public class test53 {
    String str;
    int cnt;

    test53() {
        str = "строка";
        cnt = 3;
    }

    @Override
    public String toString() {
        return "test53{" +
                "str='" + str + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
