package com.edu.umg.consumoWS;

import com.edu.umg.entity.Personal;
import com.edu.umg.entity.Puesto;
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

public class WSPersonal {
    //Agregar aca los Endpoints de cada WS
    private static final String WSLISTAR_URL=" ";
    private static final String WSINSERTAR_URL=" ";
    private static final String WSUPDATE_URL=" ";
    
    // Obtener todos los personales
    public List<Personal> obtenerPersonal() throws Exception {
        List<Personal> personalList = new ArrayList<>();
        URL url = new URL(WSLISTAR_URL);
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
            personal.setEstado((String) jsonPersonal.get("estado"));
            personal.setFecha_ingreso(DateUtil.dateFromString((String) jsonPersonal.get("fecha_ingreso")));

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
        URL url = new URL(WSINSERTAR_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        String fechaRegistro = DateUtil.dateToString(new Date()); // Aquí se obtiene la fecha actual en formato String
        // Convertir el objeto Personal a JSON
        JSONObject jsonPersonal = new JSONObject();
        jsonPersonal.put("nombre", personal.getNombre());
        jsonPersonal.put("apellido", personal.getApellido());
        jsonPersonal.put("correo", personal.getCorreo());
        jsonPersonal.put("telefono", personal.getTelefono());
        jsonPersonal.put("fecha_ingreso", fechaRegistro);
        jsonPersonal.put("estado", "Activo");
        
        // Manejo de la relación con Puesto
        if (personal.getPuesto() != null) {
            JSONObject jsonPuesto = new JSONObject();
            jsonPuesto.put("id_puesto", personal.getPuesto().getId_puesto());
            jsonPuesto.put("nombre", personal.getPuesto().getNombre());
            jsonPersonal.put("puesto", jsonPuesto); // Cambia "nombre" por "puesto"
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
        URL url = new URL(WSUPDATE_URL + "/" + personal.getId_personal());
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
        jsonPersonal.put("fecha_ingreso", DateUtil.dateToString(personal.getFecha_ingreso()));
        jsonPersonal.put("estado", personal.getEstado());

        // Manejo de la relación con Puesto
        if (personal.getPuesto() != null) {
            JSONObject jsonPuesto = new JSONObject();
            jsonPuesto.put("id_puesto", personal.getPuesto().getId_puesto());
            jsonPuesto.put("nombre", personal.getPuesto().getNombre());
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

}
