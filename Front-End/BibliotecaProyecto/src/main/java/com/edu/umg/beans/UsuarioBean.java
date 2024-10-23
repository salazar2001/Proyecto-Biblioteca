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
public class UsuarioBean implements Serializable {

    private List<Usuario> usuarios;       // Lista de usuarios para mostrar en la tabla
    private Usuario nuevoUsuario;          // Usuario nuevo para agregar
    private Usuario usuarioEditar;         // Usuario que se está editando
    private WSUsuario wsUsuario;           // Cliente WS para realizar las operaciones
    private List<String> estados; // Lista de estados

    @PostConstruct
    public void init() {
        wsUsuario = new WSUsuario();
        cargarUsuarios();                  
        nuevoUsuario = new Usuario();
        usuarioEditar = new Usuario();    

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
            wsUsuario.crearUsuario(nuevoUsuario); 
            nuevoUsuario = new Usuario();          
            cargarUsuarios();                      

            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Usuario agregado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Error al agregar el usuario: WebService no responde"));
        }
    }

    // Método para preparar la edición de un usuario
    public void prepararEdicion(Usuario usuario) {
        this.usuarioEditar = usuario; 
    }

    // Método para actualizar un usuario
    public void actualizarUsuario() {
        try {
            wsUsuario.actualizarUsuario(usuarioEditar); 
            cargarUsuarios(); 
            usuarioEditar = new Usuario(); 
            
            FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Usuario actualizado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "Error al actualizar el usuario: WebService no responde"));
            cargarUsuarios();
        }
    }
    
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

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }
    
}
