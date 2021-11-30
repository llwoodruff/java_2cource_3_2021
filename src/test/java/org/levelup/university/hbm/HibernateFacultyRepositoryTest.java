package org.levelup.university.hbm;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.levelup.university.domain.Faculty;
import org.levelup.university.domain.University;
import org.levelup.university.repository.FacultyRepository;
import org.levelup.university.repository.hbm.HibernateFacultyRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.lang.management.MonitorInfo;

public class HibernateFacultyRepositoryTest {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    private HibernateFacultyRepository facultyRepository; //реальный объект

    //BeforeAll - метод должен быть статический, выполняется один раз
    //BeforeEach - выполняется перед каждым запуском
    //AfterEach
    //AfterAll -- после всех тестов

    @BeforeEach
    public void initializeMocks() {
        factory = Mockito.mock(SessionFactory.class);

        session = Mockito.mock(Session.class);
        transaction = Mockito.mock(Transaction.class);

        //
        Mockito.when(factory.openSession()).thenReturn(session); //когда вызван метод openSession у мокафактори тогда результат возвращет объект session
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        facultyRepository = new HibernateFacultyRepository(factory); //реальный объект передаем factory из мокито
    }

    @Test
    public void shouldCreateFaculty() {
        //given
        String name = "faculty_name";
        Long universityId = 1045L;

        University university = new University();
        university.setUniversityId(universityId);

        // Mockito.when(session.load(University.class, universityId)).thenReturn(university); // без этого блока, объект University result.getUniversity возвращал бы пустоту
        Mockito.when(session.load(ArgumentMatchers.eq(University.class), ArgumentMatchers.anyLong())).thenReturn(university);
        //Либо ArgumentMatchers, либо конкретно указывать объекты


        //when
        Faculty result = facultyRepository.createFaculty(name, universityId);

        //then
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(universityId, result.getUniversity().getUniversityId());

        // verify - с помощью него надо проверять методы, которые ничего не возвращают ил возвращает пустоту
        ArgumentCaptor<Faculty> facultyCaptor = ArgumentCaptor.forClass(Faculty.class); // ерехват аргументов, которые передаются в методы МОКО
        Mockito.verify(session).persist(facultyCaptor.capture());
        Faculty capturedFaculty = facultyCaptor.getValue();
        Assertions.assertEquals(name, capturedFaculty.getName());
        //Mockito.verify(session).persist(result); // вместо result(параметры) можно также использовать ArgumentMatchers

        Mockito.verify(transaction).commit();
        //Mockito.verify(transaction, Mockito.times(0)).commit(); //для примера поломки теста. Mockito.times для проверки сколько раз вызывался метод
        //это для проверки, что был вызван метод close
        Mockito.verify(session).close();
    }

    @Test
    public void shouldCloseSessionInCreateFacultyEvenExceptionWasThrown() {
        //given
        //Это для методов, которые что-то возвращают
        Mockito.when(session.load(University.class, 1L)).thenThrow(HibernateException.class); // exception будет выброшен только если пердается 1L
        //Это для методов, которые ничего не возвращают
        Mockito.doThrow(HibernateException.class).when(session).persist(ArgumentMatchers.any(Faculty.class));
        //любой выброс исключения в тесте ведет в красному методу, это можно поправить с помощью:
        Assertions.assertThrows(
                HibernateException.class,
                () -> facultyRepository.createFaculty("faculty_name", 1L));

        //when
        //facultyRepository.createFaculty("faculty_name", 1L);
        //then
        Mockito.verify(session).close();


    }

}
