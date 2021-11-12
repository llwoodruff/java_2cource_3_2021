package org.levelup.university.repository.jdbc;

import org.levelup.university.jdbc.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFacultyRepository {

    private final DatabaseService dbService;

    public JdbcFacultyRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }

    public void getAllFaculties() {

        try (Connection connection = dbService.openConnection()) { //теперь соединение берется из ConnectionPool

        } catch (SQLException exc) { //изза такого оборачивания, соежинение закроется и не вернется в пул конекшн

        }

    }

}
