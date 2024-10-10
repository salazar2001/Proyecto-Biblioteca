package com.edu.umg.ws;

import com.edu.umg.controller.AutorController;
import com.edu.umg.controller.LibroController;
import com.edu.umg.controller.PrestamoController;
import com.edu.umg.controller.TipoController;
import com.edu.umg.controller.UsuarioController;
import com.edu.umg.controller.PersonalController;
import com.edu.umg.controller.PuestoController;

import com.edu.umg.model.Personal;
import com.edu.umg.model.Puesto;
import com.edu.umg.model.Autor;
import com.edu.umg.model.Libro;
import com.edu.umg.model.Prestamo;
import com.edu.umg.model.Tipo;
import com.edu.umg.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("Updates")
public class GenericResource {

    @Context
    private UriInfo context;
    
    private AutorController AutorCR = new AutorController();
    private LibroController LibroCR = new LibroController();
    private PrestamoController PrestamoCR = new PrestamoController();
    private TipoController TiporCR = new TipoController();
    private UsuarioController UsuarioCR = new UsuarioController();
    private PersonalController PersonalCR = new PersonalController();
    private PuestoController PuestoCR = new PuestoController();
    
    public GenericResource() {
    }


    @PUT
    @Path("/Tipos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") int id, Tipo tipos) {
        Tipo existingTipos = TiporCR.getTipoById(id);
        if (existingTipos != null) {
            tipos.setId_tipo(id); // Ensure the ID is preserved
            TiporCR.updateTipo(tipos);
            return Response.ok(tipos).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    @PUT
    @Path("/Autores/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAutores(@PathParam("id") int id, Autor autores) {
        Autor existingAutores = AutorCR.getAutorById(id);
        if (existingAutores != null) {
            autores.setId_autor(id); // Ensure the ID is preserved
            AutorCR.updateAutor(autores);
            return Response.ok(autores).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    
    @PUT
    @Path("/Usuarios/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEstudiantes(@PathParam("id") int id, Usuario usuarios) {
        Usuario existingUsuario = UsuarioCR.getUsuarioById(id);
        if (existingUsuario != null) {
            usuarios.setId_usuario(id); // Ensure the ID is preserved
            UsuarioCR.updateUsuario(usuarios);
            return Response.ok(usuarios).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    @PUT
    @Path("/Libros/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEstudiantes(@PathParam("id") int id, Libro libros) {
        Libro existingLibros = LibroCR.getLibroById(id);
        if (existingLibros != null) {
            libros.setId_libro(id); // Ensure the ID is preserved
            LibroCR.updateLibro(libros);
            return Response.ok(libros).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
  
    @PUT
    @Path("/Prestamos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrestamos(@PathParam("id") int id, Prestamo prestamos) {
        Prestamo existingPrestamos = PrestamoCR.getPrestamoById(id);
        if (existingPrestamos != null) {
            prestamos.setId_prestamo(id); // Ensure the ID is preserved
            PrestamoCR.updatePrestamo(prestamos);
            return Response.ok(prestamos).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/Personal/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonal(@PathParam("id") int id, Personal personal) {
        Personal existingPersonal = PersonalCR.getPersonalById(id);
        if (existingPersonal != null) {
            personal.setId_personal(id); // Ensure the ID is preserved
            PersonalCR.updatePersonal(personal);
            return Response.ok(personal).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/Puesto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonal(@PathParam("id") int id, Puesto puesto) {
        Puesto existingPuesto = PuestoCR.getPuestoById(id);
        if (existingPuesto != null) {
            puesto.setId_puesto(id); // Ensure the ID is preserved
            PuestoCR.updatePuesto(puesto);
            return Response.ok(puesto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
