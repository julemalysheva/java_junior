package org.example.lec02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //    Car car = new Car();
//    Заменим объявление и реализацию объекта car.
//            Пример:
        Class<?> car = Class.forName("org.example.lec02.Car");
        //? Class это и есть основа рефлексии, именно через
        //него мы и будем с ней работать! Вообще Class создаётся при загрузке любого
        //динамического класса и содержит информацию о его структуре!
        Constructor<?>[] constructor = car.getConstructors();

        Object gaz = constructor[0].newInstance("ГАЗ-311055");
        System.out.println(gaz);

        Field[] fields = gaz.getClass().getFields();
        int tmp = fields[fields.length-1].getInt(gaz);
        fields[fields.length-1].setInt(gaz, tmp+100);

        /**
         * Пример:
         * Field[] fields = gaz.getClass().getDeclaredFields();
         * for (Field field: fields) {
         * if (field.getName().equals("price")) {
         * field.setAccessible(true);
         * field.set(gaz, "Доступно!");
         * field.setAccessible(false);
         * }
         */

        Method[] methods = gaz.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }

        /**
         *  А давайте
         * уберём наследие и оставим только свои методы.
         * Пример:
         * Method[] methods = gaz.getClass().getDeclaredMethods();
         * for (int i = 0; i < methods.length; i++) {
         * System.out.println(methods[i]);
         * }
         */

    }

}
