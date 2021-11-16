package org.levelup.university.stream;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaFunctions {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();

        values.add(2);
        values.add(1);
        values.add(8);
        values.add(16);
        values.add(11);
        values.add(31);
        values.add(15);
        values.add(17);


        System.out.println(values);
      /*  values.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // <0 - o1 < o2: -1
                //  0 - o1 = o2: 0
                // >0 - o1 > o2: 1
                return -Integer.compare(o1, o2); // "-" тобы сортировать пл убыванию
            }
        });
        */
        //values.sort((val1, val2)->-Integer.compare(val1, val2));

        values.sort((val1, val2)->{
            System.out.println(val1 + "  " + val2);
            return -Integer.compare(val1, val2);
        });

        System.out.println(values);
        int maxBound = 10;
       /*

       values.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < maxBound;
                //return false;
            }
        });
        */

        //1. Интрефейс должен содержать один метод, который нужно перепределеить
        // 2. Необязательное условие. Над интерфейсом стоит аннотация @FunctionalInterface, это значит что интерфейс можно использовать как лямбду выражение

        // в () описывают аргументы метода. Если один аргуумент, то можно без скобочек -> {method body return}
        //values.removeIf((val) -> { return val < maxBound;});
        values.removeIf(val -> val < maxBound);

        /**
        values.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer -- val) {
                return integer < maxBound;
                //return false;
            }
        });
        */
       // System.out.println(values);

        // Без лямбда выражения
        System.out.println("Foreach loop");
        for(Integer val : values) {
            System.out.println("val " + val);
        }

        // Через лямбду выражение
        System.out.println("Lambda Foreach loop");
        values.forEach(val -> System.out.println("val " + val));



        //STREAM всегд возвращает новую коллекцию, старая не изменяется
        List<String> hexOctValues = values.stream() // в стриме надо вызвать какаую то операцию ,которая вызывает не стрим. map вызывает стрим.
                .map(val -> Integer.toHexString(val) + " " + Integer.toOctalString(val)) // преобразовали каждой число в 16ный формат
                .collect(Collectors.toList());

        System.out.println("Hex/Oct values");
        hexOctValues.forEach(val -> System.out.println("hex/oct " + val));

        /*
        List<Integer> v = new ArrayList<Integer>() { //Анонимный внутренний класс

            public boolean add(Integer integer){
                System.out.println("Value: " + integer);
                return  super.add(integer);
            }
        };

        v.add(5);
        */

    }
}
