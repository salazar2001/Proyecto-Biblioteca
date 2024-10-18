/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.edu.umg.wsinsertar;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author caste
 */
@Path("insertar")
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


    // Crear un nuevo autor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("autor")
    public Response createAutor(Autor autor) {
        System.out.println("WS Insert Autor");
        autorController.createAutor(autor);
        return Response.status(Response.Status.CREATED).entity(autor).build();
    }
    
        // Crear un nuevo libro
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("libro")
    public Response createLibro(Libro libro) {
        System.out.println("WS Insert Libro");
        libroController.createLibro(libro);
        return Response.status(Response.Status.CREATED).entity(libro).build();
    }

    
        // Crear un nuevo registro de Personal
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("personal")
    public Response createPersonal(Personal personal) {
        System.out.println("WS Insert Personal");
        personalController.createPersonal(personal);
        return Response.status(Response.Status.CREATED).entity(personal).build();
    }
    
        // Crear un nuevo préstamo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("prestamo")
    public Response createPrestamo(Prestamo prestamo) {
        System.out.println("WS Insert Prestamo");
        prestamoController.createPrestamo(prestamo);
        return Response.status(Response.Status.CREATED).entity(prestamo).build();
    }
    
    
    // Crear un nuevo puesto
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("puesto")
    public Response createPuesto(Puesto puesto) {
        System.out.println("WS Insert Puesto");
        puestoController.createPuesto(puesto);
        return Response.status(Response.Status.CREATED).entity(puesto).build();
    }
    
        // Crear un nuevo tipo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tipo")
    public Response createTipo(Tipo tipo) {
        System.out.println("WS Insert Tipo");
        tipoController.createTipo(tipo);
        return Response.status(Response.Status.CREATED).entity(tipo).build();
    }
    
    // Crear un nuevo usuario
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("usuario")
    public Response createUsuario(Usuario usuario) {
        System.out.println("WS Insert Usuario");
        usuarioController.createUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }
    
}
