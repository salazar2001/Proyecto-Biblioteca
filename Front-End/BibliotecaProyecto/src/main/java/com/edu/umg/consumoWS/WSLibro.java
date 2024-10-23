package com.edu.umg.consumoWS;

import com.edu.umg.entity.Autor;
import com.edu.umg.entity.Libro;
import com.edu.umg.entity.Tipo;
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

public class WSLibro {
    //Agregar aca los Endpoints de cada WS
    private static final String WSLISTAR_URL=" ";
    private static final String WSINSERTAR_URL=" ";
    private static final String WSUPDATE_URL=" ";
    // Obtener todos los libros
    public List<Libro> obtenerLibros() throws Exception {
        List<Libro> libros = new ArrayList<>();
        URL url = new URL(WSLISTAR_URL);
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
            JSONObject jsonLibro = (JSONObject) obj;
            Libro libro = new Libro();
            libro.setId_libro(((Long) jsonLibro.get("id_libro")).intValue());
            libro.setTitulo((String) jsonLibro.get("titulo"));

            // Obtener y setear el autor
            JSONObject jsonAutor = (JSONObject) jsonLibro.get("autor");
            Autor autor = new Autor();
            autor.setId_autor(((Long) jsonAutor.get("id_autor")).intValue());
            autor.setNombre((String) jsonAutor.get("nombre"));
            autor.setApellido((String) jsonAutor.get("apellido"));
            // Si tienes observaciones en la clase Autor
            if (jsonAutor.containsKey("observaciones")) {
                autor.setObservaciones((String) jsonAutor.get("observaciones"));
            }
            autor.setFecha_registro(DateUtil.dateFromString((String) jsonAutor.get("fecha_registro")));

            libro.setAutor(autor);
            
            // Obtener y setear el tipo
            JSONObject jsonTipo = (JSONObject) jsonLibro.get("tipo");
            Tipo tipo = new Tipo();
            tipo.setId_tipo(((Long) jsonTipo.get("id_tipo")).intValue());
            tipo.setNombre((String) jsonTipo.get("nombre"));
            libro.setTipo(tipo);

            libro.setIsbn((String) jsonLibro.get("isbn"));
            libro.setNumero_paginas(((Long) jsonLibro.get("numero_paginas")).intValue());
            libro.setFecha_publicacion(DateUtil.dateFromString((String) jsonLibro.get("fecha_publicacion")));
            libro.setDescripcion((String) jsonLibro.get("descripcion"));
            libro.setEstado((String) jsonLibro.get("estado"));
            libro.setFecha_ingreso(DateUtil.dateFromString((String) jsonLibro.get("fecha_ingreso")));

            libros.add(libro);
        }

        return libros;
    }

    // Crear un nuevo libro
    public void crearLibro(Libro libro) throws Exception {
        URL url = new URL(WSINSERTAR_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Libro a JSON
        JSONObject jsonLibro = new JSONObject();
        jsonLibro.put("titulo", libro.getTitulo());

        // Autor
        JSONObject jsonAutor = new JSONObject();
        jsonAutor.put("id_autor", libro.getAutor().getId_autor());
        jsonLibro.put("autor", jsonAutor);

        // Tipo
        JSONObject jsonTipo = new JSONObject();
        jsonTipo.put("id_tipo", libro.getTipo().getId_tipo());
        jsonLibro.put("tipo", jsonTipo);

        jsonLibro.put("isbn", libro.getIsbn());
        jsonLibro.put("numero_paginas", libro.getNumero_paginas());
        jsonLibro.put("fecha_publicacion", DateUtil.dateToString(libro.getFecha_publicacion())); // Convertir a String
        jsonLibro.put("descripcion", libro.getDescripcion());
        jsonLibro.put("estado", "disponible");
        jsonLibro.put("fecha_ingreso", DateUtil.dateToString(new Date())); // Fecha de ingreso actual

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonLibro.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Actualizar un libro existente
    public void actualizarLibro(Libro libro) throws Exception {
        URL url = new URL(WSUPDATE_URL + "/" + libro.getId_libro());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Libro a JSON
        JSONObject jsonLibro = new JSONObject();
        jsonLibro.put("titulo", libro.getTitulo());

        // Autor
        JSONObject jsonAutor = new JSONObject();
        jsonAutor.put("id_autor", libro.getAutor().getId_autor());
        jsonLibro.put("autor", jsonAutor);

        // Tipo
        JSONObject jsonTipo = new JSONObject();
        jsonTipo.put("id_tipo", libro.getTipo().getId_tipo());
        jsonLibro.put("tipo", jsonTipo);

        jsonLibro.put("isbn", libro.getIsbn());
        jsonLibro.put("numero_paginas", libro.getNumero_paginas());
        jsonLibro.put("fecha_publicacion", DateUtil.dateToString(libro.getFecha_publicacion())); // Convertir a String
        jsonLibro.put("descripcion", libro.getDescripcion());
        jsonLibro.put("estado", libro.getEstado());
        jsonLibro.put("fecha_ingreso", DateUtil.dateToString(libro.getFecha_ingreso())); // Convertir a String

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonLibro.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
}
