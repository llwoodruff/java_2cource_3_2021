package org.levelup.university.repository.hbm;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

@RequiredArgsConstructor //конструстор супер класса или супер конструктор?
public abstract class AbstractHibernateRepository { //

    protected final SessionFactory factory;

    //
    //
    //protected Object runWithTransaction() {
    protected <R> R runWithTransaction(Function<Session, R> invokeWithTransaction) { //Дженерик  <T> T runWithTransaction(T obj) возвращает тот тип, которые передается в метод.
        try (Session session = factory.openSession()){
            //ACID

            Transaction tx = session.beginTransaction();

            // difference
            R result = invokeWithTransaction.apply(session);

            tx.commit();

            return result;
        }
    }

}
