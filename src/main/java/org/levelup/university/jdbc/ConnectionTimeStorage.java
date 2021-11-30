package org.levelup.university.jdbc;

import java.sql.Connection;
import java.util.IdentityHashMap;
import java.util.Map;

public class ConnectionTimeStorage {

    private Map<Connection, Long> connectionsOpenTimes = new IdentityHashMap<>();

    public void put(Connection connection, long startTime){
        connectionsOpenTimes.put(connection, startTime);
    }

    //public long get(Connection connection)
}
