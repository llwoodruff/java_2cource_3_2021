package org.levelup.university.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.levelup.university.domain.Faculty;
import org.levelup.university.domain.University;
import org.postgresql.Driver;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class HibernateConfiguration {

    private static SessionFactory factory;

    // НАстройка Hibernate
    public static void configure(DatabaseConfiguration dbConfiguration) {

        Map<String, String> hibernateProperties = buildHibernateProperties(dbConfiguration);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties)
                .build();

        Configuration cfg = new Configuration()
                .addAnnotatedClass(University.class)
                .addAnnotatedClass(Faculty.class); //нужно обязательно добавлять типы классов

        factory = cfg.buildSessionFactory(ssr);
    }

    private static Map<String, String> buildHibernateProperties(DatabaseConfiguration dbConfiguration) {
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.driver_class", Driver.class.getName()); //Driver.class.getName()) возвращает полное имя класса

        properties.put("hibernate.connection.url", dbConfiguration.getUrl());
        properties.put("hibernate.connection.username", dbConfiguration.getLogin());
        properties.put("hibernate.connection.password", dbConfiguration.getPassword());

        properties.put("hibernate.show_sql", "true"); //Выводит в консоль все запросы, которые выполняет HiberNate
        properties.put("hibernate.format_sql", "true"); //Выводит в форматированном виде запросы
        //validate - сверяет название колонок. елис в классе есть, а в таблице нет, выдаст ошибку
        //update(alter table) - устраняет несоответствия между сущностей и таблицей. Может добавлять новые колнки в таблицу!!! Опасная штука
        //create - при каждом создании sessionfactory, он дропает все таблицы на которые направлены сущности. те в нашем проекте испольуется таблицу универ и она удалится
        //create-drop - здесь удаляется таблица после работы приложения
        // create используется, когда автотесты пишутся, для отладки
        // create-drop - используется для тестов
        // none - ничего не делать
        //hibernate стартует раньше Spring-a и раньше подключеия БД(миграции схемы), изза этого при запуске будет падать validate.
        // по умолчанию стоит validate
        properties.put("hibernate.hbm2ddl.auto", "validate"); //Означает как будет отрабатывать DDL команды

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            throw new RuntimeException("SessionFactory isn't configured");
        }
        return factory;
    }

}
