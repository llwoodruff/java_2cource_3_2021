package org.levelup.university.repository;

import org.levelup.university.domain.Faculty;

public interface FacultyRepository {

    Faculty createFaculty(String name, Long universityId);

}
