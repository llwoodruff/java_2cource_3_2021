package org.levelup.university.repository;


import org.levelup.university.domain.University;

import java.util.List;


public interface UniversityRepository {

    List<University> findAll();

    University createUniversity(String name, String shortName, Integer fYear);

    University deleteUniversity(/*String shortName*/ Long universityId);

    University createUniversity(String name, String shortName, Integer fYear, List<String> facultyName);

    University findByUniversityId(long universityId);

}
