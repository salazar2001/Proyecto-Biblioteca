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
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
<<<<<<< HEAD
=======
import javax.faces.bean.RequestScoped;
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "libroBean")
@ViewScoped
public class LibroBean implements Serializable {

<<<<<<< HEAD
    private List<Libro> libros;           
    private Libro nuevoLibro;              
    private Libro libroEditar;             
    private WSLibro wsLibro;               
    
    private List<Autor> autores;          
    private Autor autorSeleccionado;               
    private WSAutor wsAutor;               
=======
    private List<Libro> libros;           // Lista de libros para mostrar en la tabla
    private Libro nuevoLibro;              // Libro nuevo para agregar
    private Libro libroEditar;             // Libro que se está editando
    private WSLibro wsLibro;               // Cliente WS para realizar las operaciones de Libro
    
    private List<Autor> autores;          // Lista de autores para mostrar en la tabla
    private Autor autorSeleccionado;               // Autor nuevo para agregar   
    private WSAutor wsAutor;               // Cliente WS para realizar las operaciones de Autor
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
    
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
<<<<<<< HEAD
=======
            System.out.println("Cargando Data Tipos");
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
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
<<<<<<< HEAD
                return; 
=======
                return; // Salir si no hay autor seleccionado
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
            }

            if (tipoSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", "Debes seleccionar un tipo."));
<<<<<<< HEAD
                return; 
=======
                return; // Salir si no hay tipo seleccionado
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
            }

            nuevoLibro.setAutor(autorSeleccionado);
            nuevoLibro.setTipo(tipoSeleccionado);
<<<<<<< HEAD
            wsLibro.crearLibro(nuevoLibro);   

            nuevoLibro = new Libro();          

            cargarLibros();                    
            cargarAutores();                   
            
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Libro agregado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Error al agregar el libro: WebService no responde"));
=======
            wsLibro.crearLibro(nuevoLibro);   // Llamada al WS para agregar el libro

            nuevoLibro = new Libro();          // Limpia el formulario después de agregar

            cargarLibros();                    // Refresca la lista de libros
            cargarAutores();                   // Refresca la lista de autores
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudo agregar el libro: " + e.getMessage()));
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        }
    }


    public void seleccionarAutor(){
        if (autorSeleccionado != null) {
<<<<<<< HEAD
            libroEditar.setAutor(autorSeleccionado); 
=======
            libroEditar.setAutor(autorSeleccionado); // Asigna el puesto seleccionado al personal editado
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        }
    }
    
    public void seleccionarTipo(){
        if (tipoSeleccionado != null) {
<<<<<<< HEAD
            libroEditar.setTipo(tipoSeleccionado); 
=======
            libroEditar.setTipo(tipoSeleccionado); // Asigna el puesto seleccionado al personal editado
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        }
    }
    
    public void recargarPageLibros(){
<<<<<<< HEAD
        cargarLibros();                  
        cargarAutores();                  
        cargarTipos();
    }

=======
        cargarLibros();                   // Inicializa la lista de libros
        cargarAutores();                  // Inicializa la lista de autores
        cargarTipos();
    }

    // Getters y Setters

>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
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
