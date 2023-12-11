package org.example.lec04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Connector {

    final StandardServiceRegistry registry;
    SessionFactory sessionFactory;

    public Connector() {
        /*
        Здесь создается объект StandardServiceRegistry,
        который представляет реестр сервисов Hibernate.
        Метод configure() загружает конфигурацию Hibernate
        из файла hibernate.cfg.xml, который должен быть
        доступен в classpath. build() строит и возвращает
        финальный реестр сервисов.
         */
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        /*
         Создается объект SessionFactory, который представляет фабрику Session,
         необходимую для создания сеансов работы с Hibernate. MetadataSources(registry) загружает
         метаданные из реестра сервисов, buildMetadata() строит объект Metadata, содержащий информацию
          о сопоставлении классов сущностей и таблиц базы данных, а buildSessionFactory() создает
          и возвращает фабрику сеансов.
         */
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    /**
     * Открывается сеанс работы с Hibernate путем вызова
     * метода openSession() у SessionFactory. Объект
     * Session представляет открытое соединение с базой
     * данных для выполнения операций сущностей.
     * @return
     */
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
