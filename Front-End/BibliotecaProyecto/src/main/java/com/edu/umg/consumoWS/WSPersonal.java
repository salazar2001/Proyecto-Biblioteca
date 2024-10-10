package com.edu.umg.consumoWS;

import com.edu.umg.entity.Personal;
import com.edu.umg.entity.Puesto;
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

public class WSPersonal {
    private static final String WS_URL = "http://localhost:8080/WSBiblioteca/webresources/personal";

    // Obtener todos los personales
    public List<Personal> obtenerPersonal() throws Exception {
        List<Personal> personalList = new ArrayList<>();
        URL url = new URL(WS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
            JSONObject jsonPersonal = (JSONObject) obj;
            Personal personal = new Personal();
            personal.setId_personal(((Long) jsonPersonal.get("id_personal")).intValue());
            personal.setNombre((String) jsonPersonal.get("nombre"));
            personal.setApellido((String) jsonPersonal.get("apellido"));
            personal.setCorreo((String) jsonPersonal.get("correo"));
            personal.setTelefono((String) jsonPersonal.get("telefono"));
            personal.setFecha_ingreso(dateFromString((String) jsonPersonal.get("fecha_ingreso")));

            // Manejo de la relación con Puesto
            JSONObject jsonPuesto = (JSONObject) jsonPersonal.get("puesto");
            if (jsonPuesto != null) {
                personal.setPuesto(new Puesto());
                personal.getPuesto().setId_puesto(((Long) jsonPuesto.get("id_puesto")).intValue());
                personal.getPuesto().setNombre((String) jsonPuesto.get("nombre"));
            }

            personalList.add(personal);
        }

        return personalList;
    }

    // Crear un nuevo personal
    public void crearPersonal(Personal personal) throws Exception {
        URL url = new URL(WS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Personal a JSON
        JSONObject jsonPersonal = new JSONObject();
        jsonPersonal.put("nombre", personal.getNombre());
        jsonPersonal.put("apellido", personal.getApellido());
        jsonPersonal.put("correo", personal.getCorreo());
        jsonPersonal.put("telefono", personal.getTelefono());
        jsonPersonal.put("fecha_ingreso", dateToString(personal.getFecha_ingreso()));
        
        // Manejo de la relación con Puesto
        if (personal.getPuesto() != null) {
            JSONObject jsonPuesto = new JSONObject();
            jsonPuesto.put("id_puesto", personal.getPuesto().getId_puesto());
            jsonPersonal.put("puesto", jsonPuesto);
        }

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPersonal.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Actualizar un personal existente
    public void actualizarPersonal(Personal personal) throws Exception {
        URL url = new URL(WS_URL + "/" + personal.getId_personal());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Personal a JSON
        JSONObject jsonPersonal = new JSONObject();
        jsonPersonal.put("nombre", personal.getNombre());
        jsonPersonal.put("apellido", personal.getApellido());
        jsonPersonal.put("correo", personal.getCorreo());
        jsonPersonal.put("telefono", personal.getTelefono());
        jsonPersonal.put("fecha_ingreso", dateToString(personal.getFecha_ingreso()));

        // Manejo de la relación con Puesto
        if (personal.getPuesto() != null) {
            JSONObject jsonPuesto = new JSONObject();
            jsonPuesto.put("id_puesto", personal.getPuesto().getId_puesto());
            jsonPersonal.put("puesto", jsonPuesto);
        }

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPersonal.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Métodos de conversión de fecha
    private String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private Date dateFromString(String dateString) throws ParseException {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    }
}
