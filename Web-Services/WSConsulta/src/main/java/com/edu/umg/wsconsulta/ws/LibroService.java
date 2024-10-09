/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.wsconsulta.ws;

/**
 *
 * @author Oscar
 */

import com.edu.umg.DAO.LibroDAO;
import com.edu.umg.Entity.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class LibroService {

    private SessionFactory sessionFactory;

    public LibroService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<LibroDAO> obtenerLibrosPorNombre(String nombre) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<LibroDAO> libros = null;

        try {
            transaction = session.beginTransaction();
            libros = session.createQuery("FROM Libro WHERE nombre = :nombre", Libro.class)
                            .setParameter("nombre", nombre)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return libros;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

