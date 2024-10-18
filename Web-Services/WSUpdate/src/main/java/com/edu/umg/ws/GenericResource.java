/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.edu.umg.ws;

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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("actualizar")
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

    // Actualizar un autor existente
    @PUT
    @Path("/autor/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAutor(@PathParam("id") int id, Autor autor) {
        System.out.println("WS Update Autor");
        autor.setId_autor(id);
        autorController.updateAutor(autor);
        return Response.ok(autor).build();
    }
    
    // Actualizar un libro existente
    @PUT
    @Path("/libro/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLibro(@PathParam("id") int id, Libro libro) {
        System.out.println("WS Update Libro");
        libro.setId_libro(id);
        libroController.updateLibro(libro);
        return Response.ok(libro).build();
    }
    
    // Actualizar un registro de Personal existente
    @PUT
    @Path("/personal/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonal(@PathParam("id") int id, Personal personal) {
        System.out.println("WS Update Personal");
        personal.setId_personal(id); // Asegúrate de que la entidad Personal tenga un método setId_personal
        personalController.updatePersonal(personal);
        return Response.ok(personal).build();
    }
    
    // Actualizar un préstamo existente
    @PUT
    @Path("/prestamo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrestamo(@PathParam("id") int id, Prestamo prestamo) {
        System.out.println("WS Update Prestamo");
        prestamo.setId_prestamo(id);
        prestamoController.updatePrestamo(prestamo);
        return Response.ok(prestamo).build();
    }
    
    
    // Actualizar un puesto existente
    @PUT
    @Path("/puesto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePuesto(@PathParam("id") int id, Puesto puesto) {
        System.out.println("WS Update Puesto");
        puesto.setId_puesto(id);
        puestoController.updatePuesto(puesto);
        return Response.ok(puesto).build();
    }
    
    
    // Actualizar un tipo existente
    @PUT
    @Path("/tipo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTipo(@PathParam("id") int id, Tipo tipo) {
        System.out.println("WS Update Tipo");
        tipo.setId_tipo(id);
        tipoController.updateTipo(tipo);
        return Response.ok(tipo).build();
    }
    
    // Actualizar un usuario existente
    @PUT
    @Path("/usuario/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        System.out.println("WS Update Usuario");
        usuario.setId_usuario(id);
        usuarioController.updateUsuario(usuario);
        return Response.ok(usuario).build();
    }
    
}
