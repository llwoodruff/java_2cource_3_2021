package org.levelup.university;

import org.hibernate.SessionFactory;
import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.configuration.HibernateConfiguration;
import org.levelup.university.domain.University;
import org.levelup.university.reflect.AnnotationConfigurationPropertiesProcessor;
import org.levelup.university.repository.UniversityRepository;
import org.levelup.university.repository.hbm.HibernateUniversityRepository;

import java.sql.SQLException;
import java.util.List;

public class UniversityApplication {
    private static Object AnnotationConfigurationProperties;

    public static void main(String[] args) throws SQLException {
        String configurationFileName = "database.properties"; //иногда надо добавлять слэш "/database.properties";
        AnnotationConfigurationPropertiesProcessor.processConfigurationFile(configurationFileName);
        System.out.println("Application loaded all configuration files");

        HibernateConfiguration.configure(DatabaseConfiguration.getInstance());

        System.out.println("Hibernate has been configures successfully");
        System.out.println(DatabaseConfiguration.getInstance().toString());
        /*DatabaseService dbService = new DatabaseService(DatabaseConfiguration.getInstance());
        dbService.fillPool();*/

        SessionFactory factory = HibernateConfiguration.getSessionFactory();

        System.out.println("University application has been started");

        //UniversityRepository universityRepository = new HibernateUniversityRepository(factory);
        //University u = universityRepository.createUniversity("Высшая школа экономики", "ВШЭ", null);
        //System.out.println(u);
       /* List<University> universities = universityRepository.findAll();
        for(University u : universities){
            System.out.println(u);
        }
*/
      //  factory.close();// иначе не закроется соединение

       // Connection connection = dbService.openConnection();
       // Connection proxy = dbService.openConnection();

       // proxy.close(); //соединенние не закроется, а вернется в пул
       // System.out.println();


        /*DatabaseService dbService = new DatabaseService();
        UniversityRepository universityRepository = new JdbcUniversityRepository(dbService);

        University reu = universityRepository.createUniversity("Кубанский государственный университет", "КубГУ", 1920);
        System.out.println("Newly inserted university: " + reu.getUniversityId());

        List<University> universities = universityRepository.findAll();
        for(University u : universities){
            System.out.println(u.getShortName() + " " + u.getFoundationYear());
        }


        Connection connection = dbService.openConnection();
        System.out.println("Connection has been opened");
        connection.close();
        System.out.println("Connection closed");*/
    }
}
