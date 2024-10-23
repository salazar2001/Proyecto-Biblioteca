/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.edu.umg.wsconsulta;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
/**
 * REST Web Service
 *
 * @author Oscar
 */
@Path("WSConsulta")
public class GenericResource {
    @Context
    private UriInfo context;
    
    private AutorController autorController = new AutorController();
    private LibroController libroController = new LibroController();
    private UsuarioController usuarioController = new UsuarioController();
    private PrestamoController prestamosController = new PrestamoController();
    private TipoController tiposController = new TipoController();
    private PuestoController puestoController = new PuestoController();
    private PersonalController personalController = new PersonalController();
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }  

    @GET
    @Path("/libros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLibros() {
    List<Libro> libros = libroController.getAllLibros();
    if (libros == null || libros.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(libros).build();
}

    @GET
    @Path("/personal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonal() {
    List<Personal> personalList = personalController.getAllPersonal();  // Llama al método getAllPersonal()
    if (personalList == null || personalList.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(personalList).build();  // Devuelve la lista en formato JSON
}

    @GET
    @Path("/autores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAutores() {
    List<Autor> autores = autorController.getAllAutores();  // Llama al método getAllAutores()
    if (autores == null || autores.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(autores).build();  // Devuelve la lista de autores en formato JSON
}

    @GET
    @Path("/prestamos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrestamos() {
    List<Prestamo> prestamos = prestamosController.getAllPrestamos();  // Llama al método getAllPrestamos()
    if (prestamos == null || prestamos.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(prestamos).build();  // Devuelve la lista de préstamos en formato JSON
}

    @GET
    @Path("/puestos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPuestos() {
    List<Puesto> puestos = puestoController.getAllPuestos();  // Llama al método getAllPuestos()
    if (puestos == null || puestos.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(puestos).build();  // Devuelve la lista de puestos en formato JSON
}

    @GET
    @Path("/tipos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTipos() {
    List<Tipo> tipos = tiposController.getAllTipos();  // Llama al método getAllTipos()
    if (tipos == null || tipos.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(tipos).build();  // Devuelve la lista de tipos en formato JSON
}
    

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
    List<Usuario> usuarios = usuarioController.getAllUsuarios();  // Llama al método getAllUsuarios()
    if (usuarios == null || usuarios.isEmpty()) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(usuarios).build();  // Devuelve la lista de usuarios en formato JSON
}

   //localhost:8080/WSConsulta-BiblioProject/webresources/WSConsulta/pruebaConsulta
}
