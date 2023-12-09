package org.example.seminar3_serial.homework_sem3;

import java.io.*;

/**
 * Java Junior (семинары)
 * Урок 3. Сериализация
 * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса.
 * Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл.
 * Десериализуйте объект обратно в программу из файла.
 * Выведите все поля объекта, включая GPA, и обсудите,
 * почему значение GPA не было сохранено/восстановлено.
 */

public class Program {

    public static void main(String[] args) {

        Student student = new Student("Victoriya", 18, 4.9);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("studentsdata.bin")
        )) {
            objectOutputStream.writeObject(student);
            System.out.println("Объект Student сериализован.");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сериализации объекта Student", e);
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("studentsdata.bin")
        )) {
            try {
                student = (Student) objectInputStream.readObject();
                System.out.println("Объект Student десериализован.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Класс Student не найден", e);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при десериализации объекта Student", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при открытии файла для чтения", e);
        }

        System.out.println("Имя: " + (student != null ? student.getName() : ""));
        System.out.println("Возраст: " + (student != null ? student.getAge() : ""));
        System.out.println("Cредний балл: " + (student != null ? student.getGPA() : "N/A") + " т.к. transient");
    }

}
