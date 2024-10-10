package com.edu.umg.consumoWS;

import com.edu.umg.entity.Usuario;
import com.edu.umg.util.DateUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WSUsuario {
    private static final String WS_URL = "http://localhost:8080/WSBiblioteca/webresources/usuarios";

    // Obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
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
            JSONObject jsonUsuario = (JSONObject) obj;
            Usuario usuario = new Usuario();
            usuario.setId_usuario(((Long) jsonUsuario.get("id_usuario")).intValue());
            usuario.setNombre((String) jsonUsuario.get("nombre"));
            usuario.setApellido((String) jsonUsuario.get("apellido"));
            usuario.setCorreo((String) jsonUsuario.get("correo"));
            usuario.setTelefono((String) jsonUsuario.get("telefono"));
            usuario.setFecha_nacimiento(DateUtil.dateFromString((String) jsonUsuario.get("fecha_nacimiento")));
            usuario.setFecha_registro(DateUtil.dateFromString((String) jsonUsuario.get("fecha_registro")));
            usuario.setEstado((String) jsonUsuario.get("estado"));
            usuarios.add(usuario);
        }

        return usuarios;
    }

    // Obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(int id) throws Exception {
        URL url = new URL(WS_URL + "/" + id);
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
        JSONObject jsonUsuario = (JSONObject) parser.parse(sb.toString());
        Usuario usuario = new Usuario();
        usuario.setId_usuario(((Long) jsonUsuario.get("id_usuario")).intValue());
        usuario.setNombre((String) jsonUsuario.get("nombre"));
        usuario.setApellido((String) jsonUsuario.get("apellido"));
        usuario.setCorreo((String) jsonUsuario.get("correo"));
        usuario.setTelefono((String) jsonUsuario.get("telefono"));
        usuario.setFecha_nacimiento(DateUtil.dateFromString((String) jsonUsuario.get("fecha_nacimiento")));
        usuario.setFecha_registro(DateUtil.dateFromString((String) jsonUsuario.get("fecha_registro")));
        usuario.setEstado((String) jsonUsuario.get("estado"));

        return usuario;
    }

  

    // Crear un nuevo usuario
    public void crearUsuario(Usuario usuario) throws Exception {
        URL url = new URL(WS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String fechaRegistro = DateUtil.dateToString(new Date()); // Aquí se obtiene la fecha actual en formato String
        
        // Convertir el objeto Usuario a JSON
        JSONObject jsonUsuario = new JSONObject();
        jsonUsuario.put("nombre", usuario.getNombre());
        jsonUsuario.put("apellido", usuario.getApellido());
        jsonUsuario.put("correo", usuario.getCorreo());
        jsonUsuario.put("telefono", usuario.getTelefono());
        jsonUsuario.put("fecha_nacimiento", DateUtil.dateToString(usuario.getFecha_nacimiento())); // Convertir a String
        jsonUsuario.put("fecha_registro", fechaRegistro); // Convertir a String
        jsonUsuario.put("estado", "Activo");

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonUsuario.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Actualizar un usuario existente
    public void actualizarUsuario(Usuario usuario) throws Exception {
        URL url = new URL(WS_URL + "/" + usuario.getId_usuario());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Usuario a JSON
        JSONObject jsonUsuario = new JSONObject();
        jsonUsuario.put("nombre", usuario.getNombre());
        jsonUsuario.put("apellido", usuario.getApellido());
        jsonUsuario.put("correo", usuario.getCorreo());
        jsonUsuario.put("telefono", usuario.getTelefono());
        jsonUsuario.put("fecha_nacimiento", DateUtil.dateToString(usuario.getFecha_nacimiento())); // Convertir a String
        jsonUsuario.put("fecha_registro", DateUtil.dateToString(usuario.getFecha_registro())); // Convertir a String
        jsonUsuario.put("estado", usuario.getEstado());

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonUsuario.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

/*
    // Métodos de conversión de fecha
    private String dateToString(Date date) {
        if (date == null) {
            return null; // O puedes devolver una cadena vacía según tu preferencia
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private Date dateFromString(String dateString) throws ParseException {
        if (dateString == null || dateString.isEmpty()) {
            return null; // O puedes manejar esto de otra manera
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    }*/
}
