package com.edu.umg.beans;

import com.edu.umg.consumoWS.WSUsuario;
import com.edu.umg.entity.Usuario;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

    private List<Usuario> usuarios;       // Lista de usuarios para mostrar en la tabla
    private Usuario nuevoUsuario;          // Usuario nuevo para agregar
    private Usuario usuarioEditar;         // Usuario que se está editando
    private WSUsuario wsUsuario;           // Cliente WS para realizar las operaciones
    private String nombreBusqueda;         // Almacena el término de búsqueda
    private List<String> estados; // Lista de estados

    @PostConstruct
    public void init() {
        wsUsuario = new WSUsuario();
        cargarUsuarios();                  // Inicializa la lista de usuarios
        nuevoUsuario = new Usuario();      // Inicializa el objeto nuevoUsuario
        usuarioEditar = new Usuario();     // Inicializa el objeto usuarioEditar
        // Inicialización de la lista de estados
        estados = new ArrayList<>();
        estados.add("Activo");
        estados.add("Inactivo");
    }

    // Método para cargar la lista de usuarios
    public void cargarUsuarios() {
        try {
            usuarios = wsUsuario.obtenerUsuarios(); // Aquí debes asegurarte que este método funcione correctamente
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudieron cargar los usuarios: " + e.getMessage()));
        }
    }

    // Método para agregar un nuevo usuario
    public void agregarUsuario() {
        try {
            wsUsuario.crearUsuario(nuevoUsuario); // Llamada al servicio web para agregar el usuario
            nuevoUsuario = new Usuario();          // Limpia el formulario después de agregar
            cargarUsuarios();                      // Refresca la lista de usuarios
            // Mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Usuario agregado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "No se pudo agregar el usuario: " + e.getMessage()));
        }
    }

    // Método para preparar la edición de un usuario
    public void prepararEdicion(Usuario usuario) {
        this.usuarioEditar = usuario; // Asigna el usuario seleccionado a la propiedad usuarioEditar
    }

    // Método para actualizar un usuario
    public void actualizarUsuario() {
        try {
            wsUsuario.actualizarUsuario(usuarioEditar); // Llamada al servicio web para actualizar el usuario
            cargarUsuarios(); // Refresca la lista de usuarios después de la actualización
            usuarioEditar = new Usuario(); // Limpia el objeto usuarioEditar
            
                        // Mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Usuario actualizado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "No se pudo actualizar el usuario: " + e.getMessage()));
        }
    }
    
    // Llamar a este método en onHide para limpiar el diálogo cuando se cierre
    public void limpiarEdicion() {
        usuarioEditar = new Usuario();
    }
    
    public void actualizar(){
        cargarUsuarios();
    }

    // Getters y Setters
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }
    
}
