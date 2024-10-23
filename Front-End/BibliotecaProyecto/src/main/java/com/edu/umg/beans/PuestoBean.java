/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSPuesto;
import com.edu.umg.entity.Puesto;
<<<<<<< HEAD
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PuestoBean implements Serializable {
=======
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PuestoBean {
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
    private WSPuesto wsPuesto = new WSPuesto();
    private List<Puesto> listaPuestos;
    private Puesto nuevoPuesto = new Puesto();
    private Puesto puestoEditar = new Puesto();

<<<<<<< HEAD
    public void PuestoBean() {
=======
    public PuestoBean() {
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        // Cargar la lista de puestos al inicializar el bean
        try {
            listaPuestos = wsPuesto.obtenerPuestos();
        } catch (Exception e) {
<<<<<<< HEAD
            e.printStackTrace(); 
=======
            e.printStackTrace(); // Manejo de excepciones según tu lógica
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        }
    }

    // Método para obtener la lista de puestos
    public List<Puesto> getListaPuestos() {
        return listaPuestos;
    }

    // Método para agregar un nuevo puesto
    public void agregarPuesto() {
        try {
            wsPuesto.crearPuesto(nuevoPuesto);
<<<<<<< HEAD

            listaPuestos = wsPuesto.obtenerPuestos();
            nuevoPuesto = new Puesto(); 
            
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Puesto agregado correctamente."));
        } catch (Exception e) {
            e.printStackTrace(); 
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Error al agregar Puesto: WebService no responde "));
=======
            // Recargar la lista de puestos después de agregar
            listaPuestos = wsPuesto.obtenerPuestos();
            nuevoPuesto = new Puesto(); // Reiniciar el objeto
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones según tu lógica
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        }
    }

    // Método para preparar la edición de un puesto
    public void prepararEdicion(Puesto puesto) {
<<<<<<< HEAD
        puestoEditar = puesto; 
=======
        puestoEditar = puesto; // Asignar el puesto seleccionado a editar
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
    }

    // Método para actualizar un puesto existente
    public void actualizarPuesto() {
        try {
            wsPuesto.actualizarPuesto(puestoEditar);
<<<<<<< HEAD

            listaPuestos = wsPuesto.obtenerPuestos();
            puestoEditar = new Puesto(); 
            
            
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Puesto actualizado correctamente."));
        } catch (Exception e) {
            e.printStackTrace();
                        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Error al actualizar Puesto: WebService no responde "));
=======
            // Recargar la lista de puestos después de la actualización
            listaPuestos = wsPuesto.obtenerPuestos();
            puestoEditar = new Puesto(); // Reiniciar el objeto
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones según tu lógica
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1
        }
    }

    // Getters y Setters
    public Puesto getNuevoPuesto() {
        return nuevoPuesto;
    }

    public void setNuevoPuesto(Puesto nuevoPuesto) {
        this.nuevoPuesto = nuevoPuesto;
    }

    public Puesto getPuestoEditar() {
        return puestoEditar;
    }

    public void setPuestoEditar(Puesto puestoEditar) {
        this.puestoEditar = puestoEditar;
    }
}
