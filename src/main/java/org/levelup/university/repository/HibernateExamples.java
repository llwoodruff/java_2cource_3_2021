package org.levelup.university.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.configuration.HibernateConfiguration;
import org.levelup.university.domain.University;
import org.levelup.university.reflect.AnnotationConfigurationPropertiesProcessor;

public class HibernateExamples {
    public static void main(String[] args){
        String configurationFileName = "database.properties"; //иногда надо добавлять слэш "/database.properties";
        AnnotationConfigurationPropertiesProcessor.processConfigurationFile(configurationFileName);
        System.out.println("Application loaded all configuration files");
        HibernateConfiguration.configure(DatabaseConfiguration.getInstance());
        System.out.println("Hibernate has been configures successfully");
        System.out.println(DatabaseConfiguration.getInstance().toString());
        SessionFactory factory = HibernateConfiguration.getSessionFactory();
        System.out.println("University application has been started");

        //get/load
        University getU = null;
        University loadU = null;
        try (Session s = factory.openSession()) {
            getU = s.get(University.class, 13L); //get -
        }

        try (Session s = factory.openSession()) {
            loadU = s.load(University.class, 13L);
            System.out.println();
        }
        System.out.println(getU);
        System.out.println(loadU);
        // Lazy Loading Отложенная загрузка,
        // будет выполненн только когда будет фактическое обращение к полям. Объект находится в состоянии detached.
        // Load используется редко, когда используется связи(join?) или есть какая-то подготовка

        factory.close();

    }
}
