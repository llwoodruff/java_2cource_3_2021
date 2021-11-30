package org.levelup.university.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApiExamples {

    public static void main(String[] args){
        //SteamApi
        Collection<String> products = new ArrayList<>();
        products.add("Молоко");
        products.add("Хлеб");
        products.add("Сметана");
        products.add("Помидоры");
        products.add("Сыр");
        products.add("Колбаса");
        products.add("Йогурт");
        products.add("Авокадо");

        // Сортировка
        List<String> ascSortedProducts = products.stream()
                .sorted()
                .collect(Collectors.toList());

        printCollection(ascSortedProducts);


        List<String> descSortedProducts = products.stream()
                .sorted((p1, p2) -> -p1.compareTo(p2))
                .collect(Collectors.toList());

        printCollection(descSortedProducts);

        List<String> ascStringLengthProducts = products.stream()
                //.sorted((p1, p2) -> Integer.compare(p1.length(), p2.length()))
                .sorted(Comparator.comparingInt(string ->string.length())) // более навороченный сопосб получения длины строки
                .collect(Collectors.toList());

        printCollection(ascStringLengthProducts);

        //2. Преобразования + фильтрация
        List<Integer> lengths = products.stream()
                .map(string -> string.length()) //получим Strem<Integer> // incoming argument.anyMethod()
                .filter(length -> length > 4) //Указывается какие элименты должны бть в стриме
                .collect(Collectors.toList());
        printCollection(lengths);

        // Вторая реализация с помощью ссылки на метод
        List<Integer> lengths1 = products.stream()
                .map(String::length) //получим Strem<Integer> // incoming_argument.anyMethod()
                .filter(length -> length > 4) //Указывается какие элименты должны бть в стриме
                .collect(Collectors.toList());
        printCollection(lengths1);

        List<String> productsStarWithC = products.stream()
                .map(product -> product.toUpperCase()) //Если не надо преобразовывать типы, то вместо map можно использовать peek. Для стринговых типов не подходит
                .filter(product -> product.startsWith("С")) //Это русская С
                .collect(Collectors.toList());
        printCollection(productsStarWithC);

        // Вторая реализация с помощью ссылки на метод
        List<String> productsStarWithC1 = products.stream()
                .map(String::toUpperCase) //Если не надо преобразовывать типы, то вместо map можно использовать peek. Для стринговых типов не подходит
                .filter(product -> product.startsWith("С")) //Это русская С
                .collect(Collectors.toList());
        printCollection(productsStarWithC1);

    }

    public static void printCollection(Collection<?> collection) { //<?> принимается что в эту коллекцию принимаются любые тип. Минусы, тогда в этом методе нельзя добавить новые элементы или изменить их
        System.out.println();
        //collection.forEach(val -> System.out.println(val)); // Ниже аналог, только через ссылку на метод: System - класс out - объект println - метод
        collection.forEach(System.out::println);
    }
}
