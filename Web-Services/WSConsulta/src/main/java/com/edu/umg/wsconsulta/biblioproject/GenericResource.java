/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.edu.umg.wsconsulta.biblioproject;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

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
    
    @GET
    @Path("pruebaConsulta")
    @Produces(MediaType.TEXT_PLAIN)
    public String consultaDatos(){
        System.out.println("Hola mundo desde el WS");
        return "Hola Mundo WS";
    }
    
    @GET 
    @Path("consultaEstudiante")
    @Produces(MediaType.TEXT_PLAIN)
    public String consultaEstudiante(){
        
        return null; 
    }
    
   /* package com.edu.umg.wsconsulta.biblioproject;

import com.edu.umg.biblioteca.services.LibroService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("WSConsulta")
public class GenericResource {

    private LibroService libroService = new LibroService();

    @GET
    @Path("consultaLibro/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> consultaLibro(@PathParam("nombre") String nombre) {
        List<Libro> libros = libroService.obtenerLibrosPorNombre(nombre);
        if (libros.isEmpty()) {
            throw new NotFoundException("No se encontraron libros con el nombre: " + nombre);
        }
        return libros;
    }
}
*/
//localhost:8080/WSConsulta-BiblioProject/webresources/WSConsulta/pruebaConsulta
}
