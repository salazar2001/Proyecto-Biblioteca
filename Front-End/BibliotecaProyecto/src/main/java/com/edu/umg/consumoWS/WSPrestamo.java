package com.edu.umg.consumoWS;

import com.edu.umg.entity.Libro;
import com.edu.umg.entity.Prestamo;
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

            // Obtener y setear el libro
            JSONObject jsonLibro = (JSONObject) jsonPrestamo.get("libro");
            Libro libro = new Libro();
            libro.setId_libro(((Long) jsonLibro.get("id_libro")).intValue());
            libro.setTitulo((String) jsonLibro.get("titulo"));
            // Puedes agregar más campos del libro según lo necesites

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
        jsonPrestamo.put("fecha_prestamo", DateUtil.dateToString(prestamo.getFecha_prestamo())); // Convertir a String

        // Libro
        JSONObject jsonLibro = new JSONObject();
        jsonLibro.put("id_libro", prestamo.getLibro().getId_libro());
        jsonPrestamo.put("libro", jsonLibro);

        // Usuario
        JSONObject jsonUsuario = new JSONObject();
        jsonUsuario.put("id_usuario", prestamo.getUsuario().getId_usuario());
        jsonPrestamo.put("usuario", jsonUsuario);

        jsonPrestamo.put("fecha_devolucion", DateUtil.dateToString(prestamo.getFecha_devolucion())); // Convertir a String

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
        jsonPrestamo.put("fecha_prestamo", DateUtil.dateToString(prestamo.getFecha_prestamo())); // Convertir a String

        // Libro
        JSONObject jsonLibro = new JSONObject();
        jsonLibro.put("id_libro", prestamo.getLibro().getId_libro());
        jsonPrestamo.put("libro", jsonLibro);

        // Usuario
        JSONObject jsonUsuario = new JSONObject();
        jsonUsuario.put("id_usuario", prestamo.getUsuario().getId_usuario());
        jsonPrestamo.put("usuario", jsonUsuario);

        jsonPrestamo.put("fecha_devolucion", DateUtil.dateToString(prestamo.getFecha_devolucion())); // Convertir a String

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
