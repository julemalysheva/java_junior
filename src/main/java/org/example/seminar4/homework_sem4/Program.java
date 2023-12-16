package org.example.seminar4.homework_sem4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
Создайте базу данных (например, SchoolDB).
В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
Настройте Hibernate для работы с вашей базой данных.
Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */

public class Program {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        //Создание SessionFactory
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        //Вставка данных
        Course course = new Course("Курс 1", 30);
        System.out.println("=== Вставка данных ====");
        insertData(course);

        //Чтение данных
        System.out.println("=== Чтение данных ====");
        readData(course.getId());

        //Обновление данных
        System.out.println("=== Обновление данных ====");
        updateData(course.getId());
        readData(course.getId());

        //Удаление данных
        System.out.println("=== Удаление данных ====");
        deleteData(course.getId());
        readData(course.getId());

        //Закрытие SessionFactory по завершении работы приложения
        sessionFactory.close();
    }

    /**
     * Метод insertData вставляет новую запись в таблицу Courses.
     * @param course
     */
    public static void insertData(Course course) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();

            System.out.println("Данные успешно добавлены в таблицу Courses.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Метод readData читает запись с идентификатором id из таблицы Courses.
     * @param id
     */
    public static void readData(int id) {
        try (Session session = sessionFactory.openSession()){
            Course course = session.get(Course.class, id);
            System.out.println("Прочитана запись из таблицы Courses: " + id);
            System.out.println(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод updateData обновляет запись с идентификатором id, изменяя значение duration на 60 (для примера).
     * @param id
     */
    public static void updateData(int id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            course.setDuration(60);
            session.update(course);
            transaction.commit();
            System.out.println("Данные успешно обновлены.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод deleteData удаляет запись с идентификатором id из таблицы Courses.
     * @param id
     */
    public static void deleteData(int id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.delete(course);
            transaction.commit();

            System.out.println("Данные успешно удалены из таблицы.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
