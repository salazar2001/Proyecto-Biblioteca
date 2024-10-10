package com.edu.umg.consumoWS;

import com.edu.umg.entity.Puesto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WSPuesto {
    private static final String WS_URL = "http://localhost:8080/WSBiblioteca/webresources/puestos";

    // Obtener todos los puestos
    public List<Puesto> obtenerPuestos() throws Exception {
        List<Puesto> puestos = new ArrayList<>();
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
            JSONObject jsonPuesto = (JSONObject) obj;
            Puesto puesto = new Puesto();
            puesto.setId_puesto(((Long) jsonPuesto.get("id_puesto")).intValue());
            puesto.setNombre((String) jsonPuesto.get("nombre"));
            puestos.add(puesto);
        }

        return puestos;
    }

    // Crear un nuevo puesto
    public void crearPuesto(Puesto puesto) throws Exception {
        URL url = new URL(WS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Puesto a JSON
        JSONObject jsonPuesto = new JSONObject();
        jsonPuesto.put("nombre", puesto.getNombre());

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPuesto.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Actualizar un puesto existente
    public void actualizarPuesto(Puesto puesto) throws Exception {
        URL url = new URL(WS_URL + "/" + puesto.getId_puesto());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Puesto a JSON
        JSONObject jsonPuesto = new JSONObject();
        jsonPuesto.put("nombre", puesto.getNombre());

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPuesto.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
}
