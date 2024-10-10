package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSPersonal;
import com.edu.umg.consumoWS.WSPuesto;
import com.edu.umg.entity.Personal;
import com.edu.umg.entity.Puesto;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "personalBean")
@SessionScoped
public class PersonalBean implements Serializable {

    private List<Personal> personalList;      // Lista de personal para mostrar en la tabla
    private Personal nuevoPersonal;            // Personal nuevo para agregar
    private Personal personalEditar;           // Personal que se está editando
    private WSPersonal wsPersonal;             // Cliente WS para realizar las operaciones
    private WSPuesto wsPuesto;                 // Cliente WS para obtener puestos
    private List<Puesto> puestos;              // Lista de puestos

    @PostConstruct
    public void init() {
        wsPersonal = new WSPersonal();
        wsPuesto = new WSPuesto();
        cargarPersonal();                       // Inicializa la lista de personal
        cargarPuestos();                        // Inicializa la lista de puestos
        nuevoPersonal = new Personal();         // Inicializa el objeto nuevoPersonal
        personalEditar = new Personal();        // Inicializa el objeto personalEditar
    }

    // Método para cargar la lista de personal
    public void cargarPersonal() {
        try {
            personalList = wsPersonal.obtenerPersonal(); // Asegúrate que este método funcione correctamente
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "No se pudieron cargar el personal: " + e.getMessage()));
        }
    }

    // Método para cargar la lista de puestos
    public void cargarPuestos() {
        try {
            puestos = wsPuesto.obtenerPuestos(); // Asegúrate que este método funcione correctamente
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "No se pudieron cargar los puestos: " + e.getMessage()));
        }
    }

    // Método para agregar un nuevo personal
    public void agregarPersonal() {
        try {
            wsPersonal.crearPersonal(nuevoPersonal); // Llamada al servicio web para agregar el personal
            nuevoPersonal = new Personal();           // Limpia el formulario después de agregar
            cargarPersonal();                         // Refresca la lista de personal
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "No se pudo agregar el personal: " + e.getMessage()));
        }
    }

    // Método para preparar la edición de un personal
    public void prepararEdicion(Personal personal) {
        this.personalEditar = personal; // Asigna el personal seleccionado a la propiedad personalEditar
    }

    // Método para actualizar un personal
    public void actualizarPersonal() {
        try {
            wsPersonal.actualizarPersonal(personalEditar); // Llamada al servicio web para actualizar el personal
            cargarPersonal(); // Refresca la lista de personal después de la actualización
            personalEditar = new Personal(); // Limpia el objeto personalEditar
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "No se pudo actualizar el personal: " + e.getMessage()));
        }
    }
    
    public String irHome() {
        return "home?faces-redirect=true";
    }

    // Llamar a este método en onHide para limpiar el diálogo cuando se cierre
    public void limpiarEdicion() {
        personalEditar = new Personal();
    }

    // Getters y Setters
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    public Personal getNuevoPersonal() {
        return nuevoPersonal;
    }

    public void setNuevoPersonal(Personal nuevoPersonal) {
        this.nuevoPersonal = nuevoPersonal;
    }

    public Personal getPersonalEditar() {
        return personalEditar;
    }

    public void setPersonalEditar(Personal personalEditar) {
        this.personalEditar = personalEditar;
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }
}
