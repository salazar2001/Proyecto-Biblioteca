package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSAutor;  // Cliente WS para Autores
import com.edu.umg.consumoWS.WSLibro;
import com.edu.umg.consumoWS.WSTipo;
import com.edu.umg.entity.Autor;       // Importar la clase Autor
import com.edu.umg.entity.Libro;      // Importar la clase Libro
import com.edu.umg.entity.Tipo;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "libroBean")
@SessionScoped
public class LibroBean implements Serializable {

    private List<Libro> libros;           // Lista de libros para mostrar en la tabla
    private Libro nuevoLibro;              // Libro nuevo para agregar
    private Libro libroEditar;             // Libro que se está editando
    private WSLibro wsLibro;               // Cliente WS para realizar las operaciones de Libro
    
    private List<Autor> autores;          // Lista de autores para mostrar en la tabla
    private Autor autorSeleccionado;               // Autor nuevo para agregar   
    private WSAutor wsAutor;               // Cliente WS para realizar las operaciones de Autor
    
    private List<Tipo> tipos;
    private Tipo tipoSeleccionado;
    private WSTipo wsTipo;
    


    @PostConstruct
    public void init() {
        wsLibro = new WSLibro();          // Inicializa el cliente del Web Service de libros
        wsAutor = new WSAutor();          // Inicializa el cliente del Web Service de autores
        wsTipo = new WSTipo();
        
                cargarLibros();                   // Inicializa la lista de libros
        cargarAutores();                  // Inicializa la lista de autores
        cargarTipos();
        
        nuevoLibro = new Libro();         // Inicializa el objeto nuevoLibro       
        
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

    // Método para cargar la lista de autores
    public void cargarAutores() {
        try {
            autores = wsAutor.obtenerAutores(); // Llamada al WS para obtener autores
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudieron cargar los autores: " + e.getMessage()));
        }
    }
    
    //Metodo para obtener Tipos
    public void cargarTipos() {
        try {
            System.out.println("Cargando Data Tipos");
            tipos = wsTipo.obtenerTipos(); // Llamada al WS para obtener tipos
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudieron cargar los tipos: " + e.getMessage()));
        }
    }
    
    
    // Método para agregar un nuevo libro
    public void agregarLibro() {
        try {
            if (autorSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un autor."));
                return; // Salir si no hay autor seleccionado
            }

            if (tipoSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un tipo."));
                return; // Salir si no hay tipo seleccionado
            }

            nuevoLibro.setAutor(autorSeleccionado);
            nuevoLibro.setTipo(tipoSeleccionado);
            wsLibro.crearLibro(nuevoLibro);   // Llamada al WS para agregar el libro

            nuevoLibro = new Libro();          // Limpia el formulario después de agregar

            cargarLibros();                    // Refresca la lista de libros
            cargarAutores();                   // Refresca la lista de autores
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudo agregar el libro: " + e.getMessage()));
        }
    }


    public void seleccionarAutor(){
        if (autorSeleccionado != null) {
            libroEditar.setAutor(autorSeleccionado); // Asigna el puesto seleccionado al personal editado
        }
    }
    
    public void seleccionarTipo(){
        if (tipoSeleccionado != null) {
            libroEditar.setTipo(tipoSeleccionado); // Asigna el puesto seleccionado al personal editado
        }
    }

    // Getters y Setters

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Libro getNuevoLibro() {
        return nuevoLibro;
    }

    public void setNuevoLibro(Libro nuevoLibro) {
        this.nuevoLibro = nuevoLibro;
    }

    public Libro getLibroEditar() {
        return libroEditar;
    }

    public void setLibroEditar(Libro libroEditar) {
        this.libroEditar = libroEditar;
    }  

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }

    public void setAutorSeleccionado(Autor autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public Tipo getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(Tipo tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }
    
   

}
