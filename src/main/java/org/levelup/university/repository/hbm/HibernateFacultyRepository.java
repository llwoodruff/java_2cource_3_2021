package org.levelup.university.repository.hbm;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.university.domain.Faculty;
import org.levelup.university.domain.University;
import org.levelup.university.repository.FacultyRepository;

@RequiredArgsConstructor //сгенерирует  конструктор со всеми полями
public class HibernateFacultyRepository implements FacultyRepository {

    private final SessionFactory factory;

    @Override
    public Faculty createFaculty(String name, Long universityId) {
        try (Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setUniversity(session.load(University.class, universityId));

            tx.commit();
            return faculty;
        }
    }
}
