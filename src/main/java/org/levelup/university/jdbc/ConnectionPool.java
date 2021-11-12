package org.levelup.university.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    //Вызываем, когда необходимо соединение
    //Connection можно хранить в Map, Array, List
    private Queue<Connection> queue = new LinkedList<>();
    public Connection getConnection() {
        if (queue.isEmpty()){
            return null;
        }
        return queue.poll(); //достаем connection из очереди
    }

    // Вызываем, когда соединение больше не нужно и соединение можно вернуть в pool
    public void returnConnection(Connection connection){
        queue.offer(connection); // кладем, взятый connection в getConnection(), в конец очереди
    }

}
