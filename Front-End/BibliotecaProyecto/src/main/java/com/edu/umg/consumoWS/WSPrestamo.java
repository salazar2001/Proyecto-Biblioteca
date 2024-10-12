package com.edu.umg.consumoWS;

import com.edu.umg.entity.Autor;
import com.edu.umg.entity.Libro;
import com.edu.umg.entity.Prestamo;
import com.edu.umg.entity.Tipo;
import com.edu.umg.entity.Usuario; // O Personal, según tu diseño
import com.edu.umg.util.DateUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WSPrestamo {
    private static final String WS_URL = "http://localhost:8080/WSBiblioteca/webresources/prestamos";

    // Obtener todos los préstamos
    public List<Prestamo> obtenerPrestamos() throws Exception {
        List<Prestamo> prestamos = new ArrayList<>();
        URL url = new URL(WS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        conn.disconnect();

        // Parsear JSON
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(sb.toString());

        for (Object obj : jsonArray) {
            JSONObject jsonPrestamo = (JSONObject) obj;
            Prestamo prestamo = new Prestamo();
            prestamo.setId_prestamo(((Long) jsonPrestamo.get("id_prestamo")).intValue());

            // Obtener los datos del libro desde el JSON
            JSONObject jsonLibro = (JSONObject) jsonPrestamo.get("libro");
            Libro libro = new Libro();

            // Establecer los campos del libro
            libro.setId_libro(((Long) jsonLibro.get("id_libro")).intValue());  // ID del libro
            libro.setTitulo((String) jsonLibro.get("titulo"));  // Título
            libro.setIsbn((String) jsonLibro.get("isbn"));  // ISBN
            libro.setNumero_paginas(((Long) jsonLibro.get("numero_paginas")).intValue());  // Número de páginas
            libro.setFecha_publicacion(DateUtil.dateFromString((String) jsonLibro.get("fecha_publicacion")));  // Fecha de publicación
            libro.setDescripcion((String) jsonLibro.get("descripcion"));  // Descripción
            libro.setEstado((String) jsonLibro.get("estado"));  // Estado del libro
            libro.setFecha_ingreso(DateUtil.dateFromString((String) jsonLibro.get("fecha_ingreso")));  // Fecha de ingreso

            // Obtener y setear el autor
            JSONObject jsonAutor = (JSONObject) jsonLibro.get("autor");
            Autor autor = new Autor();
            autor.setId_autor(((Long) jsonAutor.get("id_autor")).intValue());  // ID del autor
            autor.setNombre((String) jsonAutor.get("nombre"));  // Nombre del autor
            autor.setApellido((String) jsonAutor.get("apellido"));  // Apellido del autor
            if (jsonAutor.containsKey("observaciones")) {
                autor.setObservaciones((String) jsonAutor.get("observaciones"));  // Observaciones del autor
            }
            autor.setFecha_registro(DateUtil.dateFromString((String) jsonAutor.get("fecha_registro")));  // Fecha de registro del autor
            libro.setAutor(autor);

            // Obtener y setear el tipo
            JSONObject jsonTipo = (JSONObject) jsonLibro.get("tipo");
            Tipo tipo = new Tipo();
            tipo.setId_tipo(((Long) jsonTipo.get("id_tipo")).intValue());  // ID del tipo
            tipo.setNombre((String) jsonTipo.get("nombre"));  // Nombre del tipo
            libro.setTipo(tipo);

            // Establecer el libro en el préstamo
            prestamo.setLibro(libro);

            prestamo.setLibro(libro);

            // Obtener y setear el usuario
            JSONObject jsonUsuario = (JSONObject) jsonPrestamo.get("usuario");
            Usuario usuario = new Usuario(); // O Personal
            usuario.setId_usuario(((Long) jsonUsuario.get("id_usuario")).intValue());
            usuario.setNombre((String) jsonUsuario.get("nombre"));
            // Puedes agregar más campos del usuario según lo necesites

            prestamo.setUsuario(usuario);
            prestamo.setFecha_prestamo(DateUtil.dateFromString((String) jsonPrestamo.get("fecha_prestamo")));
            prestamo.setFecha_devolucion(DateUtil.dateFromString((String) jsonPrestamo.get("fecha_devolucion")));
            prestamo.setEstado((String) jsonPrestamo.get("estado"));

            prestamos.add(prestamo);
        }

        return prestamos;
    }

    // Crear un nuevo préstamo
    public void crearPrestamo(Prestamo prestamo) throws Exception {
        URL url = new URL(WS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Prestamo a JSON
        JSONObject jsonPrestamo = new JSONObject();
        // Libro
        JSONObject jsonLibro = new JSONObject();
        jsonLibro.put("id_libro", prestamo.getLibro().getId_libro());
        jsonPrestamo.put("libro", jsonLibro);

        // Usuario
        JSONObject jsonUsuario = new JSONObject();
        jsonUsuario.put("id_usuario", prestamo.getUsuario().getId_usuario());
        jsonPrestamo.put("usuario", jsonUsuario);
        String fechaPrestamo = DateUtil.dateToString(new Date()); // Aquí se obtiene la fecha actual en formato String
        jsonPrestamo.put("fecha_prestamo", fechaPrestamo); 
        jsonPrestamo.put("fecha_devolucion", "0000-00-00");
        jsonPrestamo.put("estado", "Pendiente");
        
        
        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPrestamo.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Actualizar un préstamo existente
    public void actualizarPrestamo(Prestamo prestamo) throws Exception {
        URL url = new URL(WS_URL + "/" + prestamo.getId_prestamo());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Prestamo a JSON
        JSONObject jsonPrestamo = new JSONObject();
        // Libro
        JSONObject jsonLibro = new JSONObject();
        jsonLibro.put("id_libro", prestamo.getLibro().getId_libro());
        jsonPrestamo.put("libro", jsonLibro);

        // Usuario
        JSONObject jsonUsuario = new JSONObject();
        jsonUsuario.put("id_usuario", prestamo.getUsuario().getId_usuario());
        jsonPrestamo.put("usuario", jsonUsuario);

        jsonPrestamo.put("fecha_prestamo", DateUtil.dateToString(prestamo.getFecha_prestamo())); // Convertir a String
        String fechaDevolucion = DateUtil.dateToString(new Date());
        jsonPrestamo.put("fecha_devolucion", fechaDevolucion); // Convertir a String
        jsonPrestamo.put("estado", prestamo.getEstado());
       
        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPrestamo.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
}
