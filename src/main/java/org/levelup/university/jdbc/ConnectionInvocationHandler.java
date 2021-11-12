package org.levelup.university.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
//Библиотеки для
//CGLIB
//ByteBuddy
//JavaSist

public class ConnectionInvocationHandler implements InvocationHandler {

    private final Connection originalConnection;
    private final ConnectionPool connectionPool;

    public ConnectionInvocationHandler(Connection originalConnection, ConnectionPool connectionPool) {
        this.originalConnection = originalConnection;
        this.connectionPool = connectionPool;
    }

    public Object invoke (Object proxy, Method method, Object[] args) throws  Throwable{

        String methodName = method.getName();
        // сигнатура void close();
        if (methodName.equals("close")){
            connectionPool.returnConnection(originalConnection);
            return null;
        }

        //Вызов метода у оригинального объекта
        return method.invoke(originalConnection, args);

        //System.out.println("Перехватили вызов метод");

    }
}
