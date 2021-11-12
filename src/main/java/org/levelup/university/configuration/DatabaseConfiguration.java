package org.levelup.university.configuration;

import org.levelup.university.reflect.Property;

public class DatabaseConfiguration {

    @Property(key = "database.url")
    private String url;
    @Property(key = "database.login")
    private String login;
    @Property(key = "database.password")
    private String password;

    @Property(key = "database.min.pool.size")
    private int minPoolSize; //минимально количество открытых соединений
    @Property(key = "database.connection.timeout")
    private long connectionTimeout;
    @Property(key = "database.read.timeout")
    private long readTimeout; //

    //Singelton - design pattern
    private DatabaseConfiguration(){

    }

    public static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration(); // только в одном

    public static DatabaseConfiguration getInstance() {
        return INSTANCE;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    @Override
    public String toString() {
        return "DatabaseConfiguration{" +
                "url='" + url + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", minPoolSize=" + minPoolSize +
                ", connectionTimeout=" + connectionTimeout +
                ", readTimeout=" + readTimeout +
                '}';
    }
}
