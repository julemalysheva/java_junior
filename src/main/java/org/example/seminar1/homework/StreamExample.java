package org.example.seminar1.homework;

import java.util.Arrays;
import java.util.List;

/**
 * Напишите программу, которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class StreamExample {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        double average = integerList.stream()
                .filter(num -> num % 2 == 0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.println("Среднее значение всех четных чисел в списке: " + average);
    }
}
