package org.levelup.university.hbm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;
import org.levelup.university.configuration.TestHibernateConfiguration;
import org.levelup.university.domain.Faculty;
import org.levelup.university.domain.University;
import org.levelup.university.repository.hbm.HibernateFacultyRepository;
import org.levelup.university.repository.hbm.HibernateUniversityRepository;

public class HibernateFacultyRepositoryIT {

    private static HibernateUniversityRepository universityRepository;
    private static HibernateFacultyRepository facultyRepository;

    @BeforeAll
    public static void initializeRepositories () {
        universityRepository = new HibernateUniversityRepository(TestHibernateConfiguration.getFactory());
        facultyRepository = new HibernateFacultyRepository(TestHibernateConfiguration.getFactory());

    }

    @Test
    public void shouldCreateFaculty() {
        //given
        University university = universityRepository.createUniversity("University_1", "U1", 2000);

        String name = "Faculty_1";

        //when
        Faculty result = facultyRepository.createFaculty(name, university.getUniversityId());

        //then
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(name, result.getName());
        //Assertions.assertEquals(university, result.getUniversity());
    }


}
