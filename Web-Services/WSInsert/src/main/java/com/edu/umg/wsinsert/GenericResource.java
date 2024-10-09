package com.edu.umg.wsinsert;


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

@Path("Insertar")
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
    
    public GenericResource() {
    }
//localhost:8080/WSInsert/ws/Insertar/autores
    // Autores

    @GET
    @Path("autores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> getAutores() {
        return autorController.getAllAutores();
    }

    @GET
    @Path("autores/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutorById(@PathParam("id") int id) {
        Autor autor = autorController.getAutorById(id);
        if (autor != null) {
            return Response.ok(autor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("autores/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAutor(Autor autor) {
        autorController.createAutor(autor);
        return Response.status(Response.Status.CREATED).entity(autor).build();
    }//localhost:8080/WSInsert/ws/Insertar/autores/agregar

    @GET
    @Path("libros")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> getLibros() {
        return libroController.getAllLibros();
    }

    @GET
    @Path("libros/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibroById(@PathParam("id") int id) {
        Libro libro = libroController.getLibroById(id);
        if (libro != null) {
            return Response.ok(libro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("libros/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLibro(Libro libro) {
        libroController.createLibro(libro);
        return Response.status(Response.Status.CREATED).entity(libro).build();
    }//localhost:8080/WSInsert/ws/Insertar/libros/agregar

    // Estudiantes
    @GET
    @Path("estudiantes")
    @Produces(MediaType.APPLICATION_JSON)
        public List<Usuario> getEstudiantes() {
        return usuarioController.getAllUsuarios();
    }

    @GET
    @Path("estudiantes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstudianteById(@PathParam("id") int id) {
            Usuario usuario = usuarioController.getUsuarioById(id);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("estudiantes/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEstudiante(Usuario usuario) {
        usuarioController.createUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }//localhost:8080/WSInsert/ws/Insertar/estudiantes/agregar

    // Prestamos
    @GET
    @Path("prestamos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prestamo> getPrestamos() {
        return prestamosController.getAllPrestamos();
    }

    @GET
    @Path("prestamos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrestamoById(@PathParam("id") int id) {
        Prestamo prestamo = prestamosController.getPrestamoById(id);
        if (prestamo != null) {
            return Response.ok(prestamo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("prestamos/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrestamo(Prestamo prestamo) {
        prestamosController.createPrestamo(prestamo);
        return Response.status(Response.Status.CREATED).entity(prestamo).build();
    }//localhost:8080/WSInsert/ws/Insertar/prestamos/agregar

    // Tipos
    @GET
    @Path("tipos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tipo> getTipos() {
        return tiposController.getAllTipos();
    }

  

    @POST
    @Path("tipos/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTipo(Tipo tipo) {
        tiposController.createTipo(tipo);
        return Response.status(Response.Status.CREATED).entity(tipo).build();
    }//localhost:8080/WSInsert/ws/Insertar/tipos/agregar

    
    @GET
@Path("puestos")
@Produces(MediaType.APPLICATION_JSON)
public List<Puesto> getPuestos() {
    return puestoController.getAllPuestos();
}
 

 @POST
@Path("puestos/agregar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addPuesto(Puesto puesto) {
    puestoController.createPuesto(puesto);
    return Response.status(Response.Status.CREATED).entity(puesto).build();
}
// localhost:8080/WSInsert/ws/Insertar/puestos/agregar
   

@GET
@Path("personal")
@Produces(MediaType.APPLICATION_JSON)
public List<Personal> getPersonal() {
    return personalController.getAllPersonal();
}

@POST
@Path("personal/agregar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addPersonal(Personal personal) {
    personalController.createPersonal(personal);
    return Response.status(Response.Status.CREATED).entity(personal).build();
}
// localhost:8080/WSInsert/ws/Insertar/personal/agregar



}
