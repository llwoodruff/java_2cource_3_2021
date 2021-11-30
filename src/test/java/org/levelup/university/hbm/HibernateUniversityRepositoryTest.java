package org.levelup.university.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.levelup.university.repository.hbm.HibernateUniversityRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class) //вместо  @BeforeEach
@MockitoSettings(strictness = Strictness.LENIENT) //Нужна, тк если в каком-то тесте не будет требоваться одно из полей(где была объявлена аннотация @Mock), то падало бы с ошибкой
public class HibernateUniversityRepositoryTest {

    @Mock
    private SessionFactory factory;
    @Mock
    private Session session;
    @InjectMocks
    private HibernateUniversityRepository universityRepository; // при работе с аннотациями нужно указывать только класс, не интерфейс
/*
    @BeforeEach
    public void initializeMocks(){
        MockitoAnnotations.openMocks(this);
    }
    */
    @Test
    public void shouldReturnEmptyCollectionOfUniversities() {

    }
}
