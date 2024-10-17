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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "prestamoBean")
@ViewScoped
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
    
    private Libro actLibro;
    
    @PostConstruct
    public void init() {
        wsPrestamo = new WSPrestamo();
        wsLibro = new WSLibro();          
        wsUsuario = new WSUsuario();

        cargarPrestamo();
        
        prestamoEditar = new Prestamo();
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
    
    // Método para agregar un nuevo libro
    public void agregarPrestamo() {
        try {
            
                if ("disponible".equals(libroSeleccionado.getEstado().toLowerCase())) {
                nuevoPrestamo.setLibro(libroSeleccionado);
                libroSeleccionado.setEstado("no-disponible");
                wsLibro.actualizarLibro(libroSeleccionado);
            }
        
            nuevoPrestamo.setLibro(libroSeleccionado);
            nuevoPrestamo.setUsuario(usuarioSeleccionado);
            wsPrestamo.crearPrestamo(nuevoPrestamo);
            
            nuevoPrestamo = new Prestamo(); 
            cargarPrestamo();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudo agregar el prestamo: " + e.getMessage()));
        }
    }
    
    // Método para preparar la edición de un préstamo
    public void prepararEdicion(Prestamo prestamo) {
        this.prestamoEditar = prestamo; // Asigna el préstamo seleccionado a la propiedad prestamoEditar
        
    }
    
    
    
    // Método para actualizar un préstamo existente
    public void actualizarPrestamo() {      
        try {
                // Llamar al método del WS para actualizar el préstamo
                wsPrestamo.actualizarPrestamo(prestamoEditar);
                
        
                if("entregado".equals(prestamoEditar.getEstado().toLowerCase())){
                    Libro libro = prestamoEditar.getLibro();
                    libro.setEstado("disponible");
            
                    // Llamar al WS para actualizar el libro
                    wsLibro.actualizarLibro(libro);
                }
            
            // Limpiar la selección
            
            prestamoEditar = new Prestamo();
            cargarPrestamo();

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

        
    public void actLibros(){
        cargarLibros();
    }
    
        public void actusuario(){
        cargarUsuarios();
    }
    
    
    
    
    
    public void seleccionarLibro() throws Exception{
        if(libroSeleccionado != null){

                nuevoPrestamo.setLibro(libroSeleccionado);
        }
    }
    
    
    public void seleccionarUsuario(){
        if(usuarioSeleccionado != null){
            nuevoPrestamo.setUsuario(usuarioSeleccionado);
        }
    }
    
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Prestamo getNuevoPrestamo() {
        return nuevoPrestamo;
    }

    public void setNuevoPrestamo(Prestamo nuevoPrestamo) {
        this.nuevoPrestamo = nuevoPrestamo;
    }

    public Prestamo getPrestamoEditar() {
        return prestamoEditar;
    }

    public void setPrestamoEditar(Prestamo prestamoEditar) {
        this.prestamoEditar = prestamoEditar;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }    
    
}
