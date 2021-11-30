package org.levelup.university.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.levelup.university.domain.Faculty;
import org.levelup.university.domain.FacultyInfo;
import org.levelup.university.domain.Subject;
import org.levelup.university.domain.University;
import org.postgresql.Driver;

import java.util.HashMap;
import java.util.Map;

public class TestHibernateConfiguration {
    private static SessionFactory factory;

    public static SessionFactory getFactory(){
        return factory;
    }

    //Статистический метод инициаизации. Этот метод нельзя вызвать, он вызывается сам, когда класс откроется в памяти
    static {
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.driver_class", Driver.class.getName()); //Driver.class.getName()) возвращает полное имя класса

        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/university-it");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "1234");

        properties.put("hibernate.show_sql", "true"); //Выводит в консоль все запросы, которые выполняет HiberNate
        properties.put("hibernate.format_sql", "true"); //Выводит в форматированном виде запросы
        properties.put("hibernate.hbm2ddl.auto", "create"); //Отвечает за генерацию схемы. "create" дропает т ссоздает все объеуты заново

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        Configuration cfg = new Configuration()
                .addAnnotatedClass(University.class)
                .addAnnotatedClass(Faculty.class) //нужно обязательно добавлять типы классов
                .addAnnotatedClass(FacultyInfo.class)
                .addAnnotatedClass(Subject.class);

        factory = cfg.buildSessionFactory(ssr);

    }
}
