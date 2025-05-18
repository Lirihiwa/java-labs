package com.example.orm;

import com.example.accounts.UserProfileDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        Configuration config = new Configuration();

        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        config.setProperty("hibernate.show_sql", "true");

        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "123454321");

        config.addAnnotatedClass(UserProfileDataSet.class);

        return config.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
