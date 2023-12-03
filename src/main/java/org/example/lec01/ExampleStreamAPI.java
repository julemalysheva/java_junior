package org.example.lec01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleStreamAPI {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("Привет","мир","!","Я","родился","!");
        //Стрим АПИ позволяет преобразовыввать данные
        //теперь это поток данных:

//        list = list.stream().filter(str -> str.length() > 4).collect(Collectors.toList());
//
//        for (String item: list) {
//            System.out.println(item);
//        }

        //а можно вывести на экран без помещения в переменную и преобразований, сразу
        list.stream().filter(str -> str.length() > 4).filter(str -> str.contains("о")).forEach(System.out::println);
        //т.к. типы аргументов совпадают, мы можем заменить вызов через :: лямбды
        //можно делать цепочки вызовов и фильтров

        //метод stream создает наш поток и потом мы работаем уже с ним
//        Arrays.asList(1, 2, 3, 4, 5).stream().map(num -> num * num).forEach(System.out::println);
        //или
        Arrays.asList(1, 2, 3, 4, 5).stream().map(num -> "число: " + num + ", квадрат числа: " + num * num).forEach(System.out::println);
        Arrays.asList(1, 0, 30, 14, 5).stream().sorted().forEach(System.out::println);
        Arrays.asList(1, 0, 1, 30, 14, 5).stream().sorted().distinct().forEach(System.out::println); //distinct убирает дубли
        System.out.println("findFirst: " + Arrays.asList(1, 0, 1, 30, 14, 5).stream().sorted().distinct().findFirst().get()); //получаем первый, возвращает Оптионал

        // можно создавать потоки и другими методами.
        Arrays.asList(1, 2, 3).stream();
        //Метод asList теперь сам умеет создавать потоки данных!
                Stream.of(3, 2, 1);

/**
 * Вы можете создавать потоки с помощью Stream.of, что иногда может быть более
 * удобным. Однако это еще не все, поскольку Stream API также позволяет создавать
 * специализированные потоки для работы с примитивными типами: IntStream,
 * LongStream и DoubleStream. Поток IntStream можно использовать подобно
 * обычному циклу for(;;), используя метод range.
  */

        IntStream.range(1, 4).forEach(System.out::println);
        /**
         * У таких, вспомогательных, потоков есть пару дополнительных методов таких как
         * sum() и average().
         */
        IntStream.range(1, 4).average().ifPresent(System.out::println);

        /**
         * Следующим идет конвейерный
         * метод skip, который позволяет пропустить заданное количество первых элементов
         * потока
         * Пример:
         */
        list.stream()
                .skip(list.size()/2)
                .forEach(n -> System.out.print(n+" "));

        list.stream()
                .skip(list.size()/2)
                .filter(n -> n.length() >4)
                .filter(n -> n.toLowerCase().contains("о"))
                .forEach(n -> System.out.print(n+" "));

        /**
         * Следующим конвейерным
         * методом будет limit. Это skip наоборот! Ограничивает обработку указанным
         * количеством первых элементов.
         */
        list.stream()
                .limit(list.size()/2)
                .filter(n -> n.length() >4)
                .filter(n -> n.toLowerCase().contains("а"))
                .forEach(n -> System.out.print(n+" "));

        /**
         * пример сортировки с компаратором
         * myList.stream()
         * .sorted((s, t1) -> t1.length() - s.length())
         * .forEach(System.out::println);
         *
         * myList.stream()
         * .sorted((s, t1) -> {
         * int tmp = t1.length() - s.length();
         * if (tmp<0) return 1;
         * else if (tmp>0) return -1;
         * return 0;
         * })
         * .forEach(System.out::println);
         */



    }
}
