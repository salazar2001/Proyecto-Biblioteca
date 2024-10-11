/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSPuesto;
import com.edu.umg.entity.Puesto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class PuestoBean {
    private WSPuesto wsPuesto = new WSPuesto();
    private List<Puesto> listaPuestos;
    private Puesto nuevoPuesto = new Puesto();
    private Puesto puestoEditar = new Puesto();

    public PuestoBean() {
        // Cargar la lista de puestos al inicializar el bean
        try {
            listaPuestos = wsPuesto.obtenerPuestos();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones según tu lógica
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
            // Recargar la lista de puestos después de agregar
            listaPuestos = wsPuesto.obtenerPuestos();
            nuevoPuesto = new Puesto(); // Reiniciar el objeto
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones según tu lógica
        }
    }

    // Método para preparar la edición de un puesto
    public void prepararEdicion(Puesto puesto) {
        puestoEditar = puesto; // Asignar el puesto seleccionado a editar
    }

    // Método para actualizar un puesto existente
    public void actualizarPuesto() {
        try {
            wsPuesto.actualizarPuesto(puestoEditar);
            // Recargar la lista de puestos después de la actualización
            listaPuestos = wsPuesto.obtenerPuestos();
            puestoEditar = new Puesto(); // Reiniciar el objeto
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones según tu lógica
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
