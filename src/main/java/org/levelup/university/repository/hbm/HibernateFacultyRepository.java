package org.levelup.university.repository.hbm;

import lombok.RequiredArgsConstructor;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.university.domain.Faculty;
import org.levelup.university.domain.University;
import org.levelup.university.repository.FacultyRepository;

//18.11 @RequiredArgsConstructor //сгенерирует  конструктор со всеми полями
public class HibernateFacultyRepository extends AbstractHibernateRepository implements FacultyRepository {

    //18.11 private final SessionFactory factory;

    public HibernateFacultyRepository(SessionFactory factory){
        super(factory);
    }

    @Override
    public Faculty createFaculty(String name, Long universityId) {

            return runWithTransaction(session -> { //передаем меняющуюся часть с помощью лямбды выражения. Можно еще через анонимный класс, но больше кода будет
                Faculty faculty = new Faculty();
                faculty.setName(name);
                faculty.setUniversity(session.load(University.class, universityId)); // -> university_id -> insert
                session.persist(faculty);
                return faculty;
            });


            // сделать метод чтобы не повторять try.. catch, чтобы он принимал код и выполнял его. Но метод в метод передавать нельзя, это решается
            // тем что передется объект с каким-то методом, который переопределется

        }
    }


/***
 try (Session session = factory.openSession()){
 Transaction tx = session.beginTransaction();
 Faculty faculty = new Faculty();
 faculty.setName(name);
 faculty.setUniversity(session.load(University.class, universityId)); // -> university_id -> insert

 tx.commit();
 return faculty;

 // сделать метод чтобы не повторять try.. catch, чтобы он принимал код и выполнял его. Но метод в метод передавать нельзя, это решается
 // тем что передется объект с каким-то методом, который переопределется

 }
 *
 *
 * **/