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

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    private AutorController autorController = new AutorController();
    private LibroController libroController = new LibroController();
    private UsuarioController usuarioController = new UsuarioController();
    private PrestamoController prestamosController = new PrestamoController();
    private TipoController tiposController = new TipoController();
    private PuestoController puestoController = new PuestoController();
    private PersonalController personalController = new PersonalController();


    @Context
    private UriInfo context;

    
    @GET
    @Path("/Autor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutor(@PathParam("id") int id){
        Autor autor = autorController.getAutorById(id);
        if (autor == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(autor).build();
    }
    
  /*  @GET
    @Path("/Tipos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTipo(@QueryParam("id") Integer id, @QueryParam("nombre") String nombre) {
        Tipo result;
        if (id != null) {
            result = TiporCR.getTipoById(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            result = TiporCR.getTipoByNombre(nombre);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID o un nombre").build();
        }

        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/Autores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutor(@QueryParam("id") Integer id, @QueryParam("nombre") String nombre) {
        Autor result;
        if (id != null) {
            result = AutorCR.getAutorById(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            result = AutorCR.getAutorByNombre(nombre);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID o un nombre").build();
        }

        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/Usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@QueryParam("id") Integer id, @QueryParam("nombre") String nombre) {
        Usuario result;
        if (id != null) {
            result = UsuarioCR.getUsuarioById(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            result = UsuarioCR.getUsuarioByNombre(nombre);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID o un nombre").build();
        }

        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/Libros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibro(@QueryParam("id") Integer id, @QueryParam("nombre") String nombre) {
        Libro result;
        if (id != null) {
            result = LibroCR.getLibroById(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            result = LibroCR.getLibroByNombre(nombre);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID o un nombre").build();
        }

        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/Prestamos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrestamo(@QueryParam("id") Integer id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID").build();
        }

        Prestamo existingPrestamo = PrestamoCR.getPrestamoById(id);
        if (existingPrestamo != null) {
            return Response.ok(existingPrestamo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/Personal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonal(@QueryParam("id") Integer id, @QueryParam("nombre") String nombre) {
        Personal result;
        if (id != null) {
            result = PersonalCR.getPersonalById(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            result = PersonalCR.getPersonalByNombre(nombre);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID o un nombre").build();
        }

        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/Puestos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuesto(@QueryParam("id") Integer id, @QueryParam("nombre") String nombre) {
        Puesto result;
        if (id != null) {
            result = PuestoCR.getPuestoById(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            result = PuestoCR.getPuestoByNombre(nombre);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar un ID o un nombre").build();
        }

        if (result != null) {
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
*/

//localhost:8080/WSConsulta-BiblioProject/webresources/WSConsulta/pruebaConsulta
}
