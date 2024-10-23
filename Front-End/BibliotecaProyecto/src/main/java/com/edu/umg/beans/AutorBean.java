package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSAutor;  // Cliente WS para Autores
import com.edu.umg.entity.Autor;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "autorBean")
@ViewScoped
public class AutorBean implements Serializable {

    private List<Autor> autores;        // Lista de autores para mostrar en la tabla
    private Autor nuevoAutor;           // Autor nuevo para agregar
    private Autor autorEditar;          // Autor que se está editando
    private WSAutor wsAutor;            // Cliente WS para realizar las operaciones

    @PostConstruct
    public void init() {
        wsAutor = new WSAutor();        // Inicializa el cliente del Web Service
        cargarAutores();                // Inicializa la lista de autores
        nuevoAutor = new Autor();       // Inicializa el objeto nuevoAutor
        autorEditar = new Autor();      // Inicializa el objeto autorEditar
    }

    // Método para cargar la lista de autores
    public void cargarAutores() {
        try {
            autores = wsAutor.obtenerAutores(); // Llamada al WS para obtener autores
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Error al cargar los autores: WebService no responde"));
        }
    }

    // Método para agregar un nuevo autor
    public void agregarAutor() {
        try {
            wsAutor.crearAutor(nuevoAutor); // Llamada al WS para agregar el autor
            nuevoAutor = new Autor();       // Limpia el formulario después de agregar
            cargarAutores();                // Refresca la lista de autores
            
                        // Mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Autor agregado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Error al agregar el autor: WebService no responde "));
        }
    }

    // Método para preparar la edición de un autor
    public void prepararEdicion(Autor autor) {
        this.autorEditar = autor; // Asigna el autor seleccionado a la propiedad autorEditar
    }

    // Método para actualizar un autor
    public void actualizarAutor() {
        try {
            wsAutor.actualizarAutor(autorEditar); // Llamada al WS para actualizar el autor
            cargarAutores(); // Refresca la lista de autores después de la actualización
            autorEditar = new Autor(); // Limpia el objeto autorEditar
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "Error al actualizar el autor: WebService no responde"));
            cargarAutores();
        }
    }
    
    // Llamar a este método en onHide para limpiar el diálogo cuando se cierre
    public void limpiarEdicion() {
        autorEditar = new Autor();
    }

    // Getters y Setters
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Autor getNuevoAutor() {
        return nuevoAutor;
    }

    public void setNuevoAutor(Autor nuevoAutor) {
        this.nuevoAutor = nuevoAutor;
    }

    public Autor getAutorEditar() {
        return autorEditar;
    }

    public void setAutorEditar(Autor autorEditar) {
        this.autorEditar = autorEditar;
    }
}
