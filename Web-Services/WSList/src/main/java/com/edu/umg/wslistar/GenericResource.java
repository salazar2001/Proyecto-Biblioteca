/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.edu.umg.wslistar;

import com.edu.umg.controller.AutorController;
import com.edu.umg.controller.LibroController;
import com.edu.umg.controller.PersonalController;
import com.edu.umg.controller.PrestamoController;
import com.edu.umg.controller.PuestoController;
import com.edu.umg.controller.TipoController;
import com.edu.umg.controller.UsuarioController;
import com.edu.umg.model.Autor;
import com.edu.umg.model.Libro;
import com.edu.umg.model.Personal;
import com.edu.umg.model.Prestamo;
import com.edu.umg.model.Puesto;
import com.edu.umg.model.Tipo;
import com.edu.umg.model.Usuario;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jose5
 */
@Path("listar")
public class GenericResource {

    @Context
    private UriInfo context;

    private AutorController autorController = new AutorController();
    private LibroController libroController = new LibroController();
    private PersonalController personalController = new PersonalController();
    private PrestamoController prestamoController = new PrestamoController();
    private PuestoController puestoController = new PuestoController();
    private TipoController tipoController = new TipoController();
    private UsuarioController usuarioController = new UsuarioController();
    
    // Obtener todos los autores
    @GET
    @Path("/autores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> getAutores() {
        return autorController.getAllAutores();
    }
    
    // Obtener todos los libros
    @GET
    @Path("/libros")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> getAllLibros() {
        return libroController.getAllLibros();
    }

    // Obtener todos los registros de Personal
    @GET
    @Path("/personal")
    @Produces(MediaType.APPLICATION_JSON) // Cambiar a JSON para una mejor interoperabilidad
    public List<Personal> getAllPersonal() {
        return personalController.getAllPersonal();
    }
    
    // Obtener todos los préstamos
    @GET
    @Path("/prestamos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prestamo> getPrestamos() {
        return prestamoController.getAllPrestamos();
    }
    
    // Obtener todos los puestos
    @GET
    @Path("/puestos")
    @Produces(MediaType.APPLICATION_JSON) // Cambiar a JSON para una mejor interoperabilidad
    public List<Puesto> getPuestos() {
        return puestoController.getAllPuestos();
    }
    
    // Obtener todos los tipos
    @GET
    @Path("/tipos")
    @Produces(MediaType.APPLICATION_JSON) // Cambiar a JSON para una mejor interoperabilidad
    public List<Tipo> getTipos() {
        return tipoController.getAllTipos();
    }
    
        // Obtener todos los usuarios
    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() {
        return usuarioController.getAllUsuarios();
    }
    
    
    
}
