package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Andrei2001.";
    private static final String DRIVER_CLASS ="com.mysql.cj.jdbc.Driver";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties connectionProperties = new Properties();
            connectionProperties.put("hibernate.connection.url", URL);
            connectionProperties.put("hibernate.connection.username", USER);
            connectionProperties.put("hibernate.connection.password", PASSWORD);
            connectionProperties.setProperty("hibernate.connection.driver_class",DRIVER_CLASS);

            configuration.setProperties(connectionProperties);
            configuration.addAnnotatedClass(User.class);

            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
            );
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
