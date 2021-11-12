package org.levelup.university.repository.jdbc;

import org.levelup.university.domain.University;



import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.levelup.university.jdbc.DatabaseService;
import org.levelup.university.repository.UniversityRepository;

import static java.lang.System.out;

public class JdbcUniversityRepository implements UniversityRepository{

    private final DatabaseService dbService;

    public JdbcUniversityRepository(DatabaseService dbService){
        this.dbService = dbService;
    }

    @Override
    public University findByUniversityId(long universityId) {
        return null;
    }

    @Override
    public  University deleteUniversity(/*String shortName */Long universityId){
        try (Connection connection = dbService.openConnection()){
            System.out.println("Connection has been open");
            PreparedStatement stmt = connection.prepareStatement("delete from university where id = ?"); //("select * from university where id = ?");
            stmt.setLong(1, 2);
            stmt.executeUpdate();
            /*
            PreparedStatement stmt = connection.prepareStatement("delete from university where short_name = ?"); //("select * from university where id = ?");
            stmt.setString(1, shortName);
            stmt.executeUpdate();
*/
            //stmt.executeQuery();


            //out.println("Select by id  " + createdRows);

           // stmt.setInt(1, universityId);
            /*if(universityId != null) {
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    stmt.executeUpdate("delete from university where id = ?");
                }
                else {
                    out.println("There is no such university id ");
                }
            } else {
                out.println("Entered universityId to delete");
            }*/

        } catch (SQLException exc){
            System.out.println("Couldn't open connection");
        }
        return null;
    }


    @Override
    public  University createUniversity(String name, String shortName, Integer fYear){
        try (Connection connection = dbService.openConnection()){
            PreparedStatement stmt = connection.prepareStatement("insert into university(name, short_name, foundation_year) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name); //Для вставки данных. Происходит экранирование символов
            stmt.setString(2,shortName);
            if(fYear == null) {
               stmt.setNull(3, JDBCType.INTEGER.getVendorTypeNumber());
            } else {
                stmt.setInt(3, fYear); //-> NPE(not point exception) получим исключение если передалим null
            }

            int createdRows = stmt.executeUpdate();//insert/update/delete. executeUpdate - возвращает int - количество измененных строк. Есть еще метод execute(), он возвращает boolean
            //нужно сделать обработку исключений при вставке повторяющегося института. вопрос как?

            System.out.println("Count rows: " + createdRows);
            ResultSet generatedKeysSet = stmt.getGeneratedKeys();
            generatedKeysSet.next();
            Long universityId = generatedKeysSet.getLong(1);

            return new University(universityId, name, shortName, fYear);

        } catch (SQLException exc){
            System.out.println("Couldn't");
        }
        return null;
    }

   @Override
    public List<University> findAll(){
        try (Connection connection = dbService.openConnection()){
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select id, name, short_name as shortName, foundation_year AS fYear from university");

            return retrieveFromResultSet(resultSet);

        } catch (SQLException exc) {
            out.println("Couldn't get all university because of an error: " + exc.getMessage());
            return Collections.emptyList(); //чтобы не падал проект на исключение. Возвращаем пустой лист
        }
    }

    private List<University> retrieveFromResultSet(ResultSet resultSet) throws SQLException {
        //ResultSet - ~Iterator ессть некий курсор, который указывает на конкретную строчку из резельтирующего множества. Один метод next - проверяет есть ли строчка дальше и переходит к ней
        List<University> universities = new ArrayList<>();

        while(resultSet.next()){
            Long universityId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String shortName = resultSet.getString("shortName");
            Integer fYear = resultSet.getInt("fYear");

            University university = new University(universityId, name, shortName, fYear);
            universities.add(university);

        }
        return universities;
    }
}

