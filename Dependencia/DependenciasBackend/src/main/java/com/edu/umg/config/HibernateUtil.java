package com.edu.umg.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Usar el DataSource JNDI configurado en Tomcat
                configuration.setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/MyDB");

                // Configuración de Hibernate
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

                // Añadir las clases de entidad
                configuration.addAnnotatedClass(com.edu.umg.model.Usuario.class);
                configuration.addAnnotatedClass(com.edu.umg.model.Autor.class);
                configuration.addAnnotatedClass(com.edu.umg.model.Tipo.class);
                configuration.addAnnotatedClass(com.edu.umg.model.Libro.class);
                configuration.addAnnotatedClass(com.edu.umg.model.Prestamo.class);
                configuration.addAnnotatedClass(com.edu.umg.model.Puesto.class);
                configuration.addAnnotatedClass(com.edu.umg.model.Personal.class);
                
                sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
