package org.example.seminar2.homework.task1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Задача 1:
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */

public class Program {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        animals[0] = new Dog("Герда", 7, "Такса");
        animals[1] = new Cat("Мурзик", 5, "Белый");

        for (Animal animal: animals) {

            Class<?> animalClass = animal.getClass();
            Class<?> superClass = animalClass.getSuperclass();

            System.out.println("Animal: " + animalClass.getSimpleName());

            if (superClass != null) {
                printField(animal, superClass);
            }

            printField(animal, animalClass);

            try {
                Method methodMakeSound = animalClass.getMethod("makeSound");
                methodMakeSound.invoke(animal);
            } catch (NoSuchMethodException e) {
                System.out.println("Метод makeSound не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
        }
    }

    private static void printField(Animal animal, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + ": " + field.get(animal));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
