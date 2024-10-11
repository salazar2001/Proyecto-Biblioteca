/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSLibro;
import com.edu.umg.consumoWS.WSPrestamo;
import com.edu.umg.consumoWS.WSUsuario;
import com.edu.umg.entity.Libro;
import com.edu.umg.entity.Prestamo;
import com.edu.umg.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "prestamoBean")
@SessionScoped
public class PrestamoBean implements Serializable {
    
    private List<Prestamo> prestamos;
    private Prestamo nuevoPrestamo;
    private Prestamo prestamoEditar;
    private WSPrestamo wsPrestamo;
    
    private List<Libro> libros;
    private Libro libroSeleccionado;
    private WSLibro wsLibro;
    
    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;
    private WSUsuario wsUsuario;
    
    @PostConstruct
    public void init() {
        wsPrestamo = new WSPrestamo();
        wsLibro = new WSLibro();          
        wsUsuario = new WSUsuario();

        cargarPrestamo();
        cargarLibros();
        cargarUsuarios();
        
        nuevoPrestamo = new Prestamo();         // Inicializa el objeto nuevoLibro       
        
    }
    
    // Método para cargar la lista de Prestamos
    public void cargarPrestamo() {
        try {
            prestamos = wsPrestamo.obtenerPrestamos(); // Llamada al WS para obtener Prestamo
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudieron cargar los prestamos: " + e.getMessage()));
        }
    }
    
    // Método para cargar la lista de libros
    public void cargarLibros() {
        try {
            libros = wsLibro.obtenerLibros(); // Llamada al WS para obtener libros
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudieron cargar los libros: " + e.getMessage()));
        }
    }
    
        // Método para cargar la lista de Usuarios
    public void cargarUsuarios() {
        try {
            usuarios = wsUsuario.obtenerUsuarios(); // Llamada al WS para obtener libros
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudieron cargar los usuarios: " + e.getMessage()));
        }
    }
    
    // Método para preparar la edición de un préstamo
    public void prepararEdicion(Prestamo prestamo) {
        this.prestamoEditar = prestamo; // Asigna el préstamo seleccionado a la propiedad prestamoEditar
    }

    
    // Método para agregar un nuevo libro
    public void agregarPrestamo() {
        try {
            if (usuarioSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un usuario."));
                return;
            }

            if (libroSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar el libro."));
                return; // Salir si no hay tipo seleccionado
            }

            nuevoPrestamo.setUsuario(usuarioSeleccionado);
            nuevoPrestamo.setLibro(libroSeleccionado);
            wsPrestamo.crearPrestamo(nuevoPrestamo);
            
            nuevoPrestamo = new Prestamo();

            cargarLibros();                    // Refresca la lista de libros
            cargarUsuarios();                   // Refresca la lista de autores
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudo agregar el prestamo: " + e.getMessage()));
        }
    }
    
    // Método para actualizar un préstamo existente
    public void actualizarPrestamo() {
        try {
            // Verificar que un préstamo esté seleccionado
            if (prestamoEditar == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un préstamo para actualizar."));
                return;
            }

            // Verificar que un usuario esté seleccionado
            if (usuarioSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un usuario."));
                return;
            }

            // Verificar que un libro esté seleccionado
            if (libroSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un libro."));
                return;
            }

            // Configurar los detalles del préstamo a actualizar
            prestamoEditar.setUsuario(usuarioSeleccionado);
            prestamoEditar.setLibro(libroSeleccionado);

            // Llamar al método del WS para actualizar el préstamo
            wsPrestamo.actualizarPrestamo(prestamoEditar);

            // Limpiar la selección
            prestamoEditar = null; // Limpiar el préstamo en edición

            // Refrescar las listas después de la actualización
            cargarLibros();    // Refresca la lista de libros
            cargarUsuarios();   // Refresca la lista de usuarios

            // Mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Éxito", "Préstamo actualizado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudo actualizar el préstamo: " + e.getMessage()));
        }
    }


    
    
    
    
    
    
}
