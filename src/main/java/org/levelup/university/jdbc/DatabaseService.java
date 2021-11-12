package org.levelup.university.jdbc;

import org.levelup.university.configuration.DatabaseConfiguration;

import java.lang.reflect.Proxy;
//import java.net.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// для 3 домашнего задания надо будет создать интерфейс для DatabaseService
public class DatabaseService {

    private DatabaseConfiguration dbConfiguration;
    private ConnectionPool connectionPool;

    public DatabaseService(DatabaseConfiguration dbConfiguration) {
        this.dbConfiguration = dbConfiguration;
        this.connectionPool = new ConnectionPool();
    }

    public void fillPool() {
        for(int i = 0; i < dbConfiguration.getMinPoolSize(); i++){
            Connection connection = createConnection();
            connectionPool.returnConnection(connection);
        }
    }

    private Connection createConnection(){
        try {
          return DriverManager.getConnection(
                  dbConfiguration.getUrl(),
                  dbConfiguration.getLogin(),
                  dbConfiguration.getPassword()
          );
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
    }


    public Connection openConnection() {
        Connection connection = connectionPool.getConnection();
        return(Connection) Proxy.newProxyInstance(
                connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(),
                new ConnectionInvocationHandler(connection, connectionPool)
                //(proxy, method, args) -> null
        );


    }
}

/*
 try{
            return DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/university",/*jdbc url это похоже на обчные ссылки. Пример, jdbc - это протокол jdbc:<vendor_name>://<host(dns(localhost)/ip(127.0.0.1))>:<port>/<database_name>?<key1>=<val1>&<key2>=<val2>... */
                   /* "postgres" ,
                            "1234"
                            );
                            } catch (SQLException exc){
                            System.out.println("Couldn't connect to database");
                            throw  new RuntimeException(exc);
                            }
 */
