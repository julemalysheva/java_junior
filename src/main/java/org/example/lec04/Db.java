package org.example.lec04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static void con() {
        //Операция удаления
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Transaction t = session.beginTransaction();
            List<Magic> magics = session.createQuery("FROM Magic", Magic.class).getResultList();
            magics.forEach(session::delete);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Операция изменения данных
//        Connector connector = new Connector();
//        try (Session session = connector.getSession()) {
//            String hql = "from Magic where id = :id";
//            Query<Magic> query = session.createQuery(hql, Magic.class);
//            query.setParameter("id", 4);
//            Magic magic = query.getSingleResult();
//            System.out.println(magic);
//            magic.setAttBonus(100);
//            magic.setName("Ярость");
//            session.beginTransaction();
//            session.update(magic);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //Операция чтения записей из БД
//        Connector connector = new Connector();
//        try (Session session = connector.getSession()) {
//            /*
//             Выполняется запрос к базе данных для получения всех записей типа Magic из таблицы. Метод createQuery()
//             создает запрос HQL (Hibernate Query Language), а метод getResultList() выполняет этот запрос и возвращает
//             результат в виде списка сущностей Magic.
//             */
//            List<Magic> books = session.createQuery("FROM Magic", Magic.class).getResultList();
//            books.forEach(System.out::println);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //Пример сохранения созданных объектов в БД
        /*Connector connector = new Connector();
        Session session = connector.getSession();
        Magic magic = new Magic("Волшебная стрела", 10, 0, 0);
//        Начинается транзакция работы с базой данных. Метод beginTransaction() вызывается у Session для начала новой транзакции.
        session.beginTransaction();
//        Выполняется сохранение объекта magic в базу данных при помощи метода save(). Этот метод говорит Hibernate о том,
//        что объект является новой сущностью, которая должна быть сохранена в базе данных.
        session.save(magic);
        magic = new Magic("Молния", 25, 0, 0); session.save(magic);
        magic = new Magic("Каменная кожа", 0, 0, 6); session.save(magic);
        magic = new Magic("Жажда крови", 0, 6, 0); session.save(magic);
        magic = new Magic("Жажда крови", 0, 6, 0); session.save(magic);
        magic = new Magic("Проклятие", 0, -3, 0); session.save(magic);
        magic = new Magic("Лечение", -30, 0, 0); session.save(magic);

//Фиксируется транзакция путем вызова commit(). Это применяет все изменения, выполненные в
//рамках текущей транзакции, и сохраняет их в базе данных.
        session.getTransaction().commit();
//        Закрывается сеанс работы с Hibernate, освобождая ресурсы и разрывая соединение с базой данных.
        session.close();*/

//******************************************************************************************************************
        //Пример работы с JDBC - разбить по методами для наглядности
//        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            Statement statement = con.createStatement();
//            statement.execute("DROP SCHEMA `test`");
//            statement.execute("CREATE SCHEMA `test`");
//            statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstname` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, PRIMARY KEY (`id`));");
//            statement.execute("INSERT INTO `test`.`table` (`id`, `firstname`, `lastname`)\n" +
//                    " VALUES (1, 'Иванов', 'Иван');");
//            statement.execute("INSERT INTO `test`.`table` (`id`, `firstname`, `lastname`)\n" +
//                    " VALUES (2, 'Петров', 'Петр');");
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM test.table;");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(3) + " " + resultSet.getString(2) + " "
//                + resultSet.getInt(1));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException();
//        }

    }
}
