package org.levelup.university.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.postgresql.jdbc.PgConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionPoolTest {

    @Test
    // testGetConnection_whenQuesIsEmpty_thenReturnNull test <Method Name>_when<Условия>_then<Ожидаемы результат>

    //ShouldReturnNullIfQueuesIsEmpty
    @DisplayName("getConnection(): should return null if queue is empty")
    public void test() {
        //given     //given     //where
        //when      //where
        //then

        //given
        ConnectionPool pool = new ConnectionPool();

        //when
        Connection result = pool.getConnection();

        //then
        Assertions.assertNull(result);

    }
    @Test
    public void shouldOfferConnectionToPool() throws SQLException {
        //given
        ConnectionPool pool = new ConnectionPool();
        Connection connection = Mockito.mock(Connection.class); //Proxy


        //when
        pool.returnConnection(connection);

        // then
        Connection result = pool.getConnection();
        Assertions.assertSame(connection, result);
    }
}