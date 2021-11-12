package org.levelup.university.repository.hbm;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.university.domain.University;
import org.levelup.university.repository.UniversityRepository;

import java.util.List;

@RequiredArgsConstructor // конструктор с final полями -
public class HibernateUniversityRepository implements UniversityRepository {

    private final SessionFactory factory;

    public List<University> findAll() {
        try (Session session = factory.openSession()){
            return session.createQuery(" from University", University.class)
                    // HQL  Hibernate Query Language
                    // select * from University where shortName = '...'
                    // from University
                    .getResultList(); //Список строк, преобразованных в объекты сущности
        }
    }

    @Override
    public University deleteUniversity(Long universityId) {
        return null;
    }

    @Override
    public University findByUniversityId(long universityId) {
        try (Session session = factory.openSession()){
            return session.createQuery("from University where universityId = :uid", University.class) //:uid - ключб установка параметра по ключу
                    .setParameter("uid", universityId) //сразу  presies
                    .getSingleResult();// можно использовать если вернется только ОДНА строка или пустотв, иначе  ошибка NonUniqueResultExcpetion

        }
    }

    public University createUniversity(String name, String shortName, Integer fYear){

        try (Session session = factory.openSession()){ //если try c ресурсами подключение закрывается автоматически
            Transaction tx = session.beginTransaction(); //то нуж всегда когда делаются измнения данных в тблицах transient

            University u = new University(name, shortName, fYear);
            session.persist(u); // этот метод persist вставляет данные //persistent
            System.out.println("Newly inserts row Id: " + u.getUniversityId());

            tx.commit(); //фиксирование транзакции
            return u;
        }
    }
}
