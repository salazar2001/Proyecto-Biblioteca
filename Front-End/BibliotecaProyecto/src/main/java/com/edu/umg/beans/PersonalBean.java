package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSPersonal;
import com.edu.umg.consumoWS.WSPuesto;
import com.edu.umg.entity.Personal;
import com.edu.umg.entity.Puesto;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "personalBean")
@ViewScoped
public class PersonalBean implements Serializable {

    private List<Personal> personalList;    // Lista de personal para mostrar en la tabla
    private Personal nuevoPersonal;         // Personal nuevo para agregar
    private Personal personalEditar;        // Personal que se está editando
    private WSPersonal wsPersonal;          // Cliente WS para realizar las operaciones

    private WSPuesto wsPuesto;              // Cliente WS para obtener puestos
    private List<Puesto> puestos;           // Lista de puestos
    private Puesto puestoSeleccionado;      // Puesto seleccionado en el diálogo

    @PostConstruct
    public void init() {
        wsPersonal = new WSPersonal();
        wsPuesto = new WSPuesto();
        cargarPersonal();                   // Inicializa la lista de personal
        cargarPuestos();                    // Inicializa la lista de puestos
        nuevoPersonal = new Personal();     // Inicializa el objeto nuevoPersonal
        personalEditar = new Personal();    // Inicializa el objeto personalEditar
    }

    // Método para cargar la lista de personal
    public void cargarPersonal() {
        try {
            personalList = wsPersonal.obtenerPersonal();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cargar el personal: " + e.getMessage()));
        }
    }

    // Método para cargar la lista de puestos
    public void cargarPuestos() {
        try {
            puestos = wsPuesto.obtenerPuestos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cargar los puestos: " + e.getMessage()));
        }
    }
    
    // Prepara la edición cargando los datos del personal seleccionado
    public void prepararEdicion(Personal personal) {
        this.personalEditar = personal;
        this.puestoSeleccionado = personal.getPuesto();
    }

    // Método para agregar un nuevo personal
    public void agregarPersonal() {
        try {
            nuevoPersonal.setPuesto(puestoSeleccionado);  
            wsPersonal.crearPersonal(nuevoPersonal);      
            nuevoPersonal = new Personal();
            cargarPersonal();   
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Persona agregada correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al agregar el persona: WebService no responde "));
        }
    }

    // Método para actualizar el personal editado
    public void actualizarPersonal() {
        try {
            personalEditar.setPuesto(puestoSeleccionado);  
            wsPersonal.actualizarPersonal(personalEditar); 
            cargarPersonal();   
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Persona actualizada correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar el persona: WebService no responde "));
            cargarPersonal();   
        }
    }

    // Método para seleccionar un puesto
    public void seleccionarPuesto() {
        if (puestoSeleccionado != null) {
            personalEditar.setPuesto(puestoSeleccionado); 
        }
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

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public Puesto getPuestoSeleccionado() {
        return puestoSeleccionado;
    }

    public void setPuestoSeleccionado(Puesto puestoSeleccionado) {
        this.puestoSeleccionado = puestoSeleccionado;
    }

}
