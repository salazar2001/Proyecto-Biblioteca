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
        //Agregar aca los Endpoints de cada WS
    private static final 
            String WSLISTAR_URL="http://192.168.0.100:8083/WSListar-1.0-SNAPSHOT/ws/listar/puestos";
    private static final 
            String WSINSERTAR_URL="http://192.168.0.110:8082/WSInsertar-1.0-SNAPSHOT/ws/insertar/puesto";
    private static final 
            String WSUPDATE_URL="http://192.168.0.104:8084/WSUpdate-1.0-SNAPSHOT/ws/actualizar/puesto";
    // Obtener todos los puestos
    public List<Puesto> obtenerPuestos() throws Exception {
        List<Puesto> puestos = new ArrayList<>();
<<<<<<< HEAD
        URL url = new URL("http://192.168.0.103:8080/WSListar/ws/listar/puestos");
=======
        URL url = new URL(WSLISTAR_URL);
>>>>>>> b4f924053c3b59ec7d7af87494f01e99e53b4c57
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
<<<<<<< HEAD
        URL url = new URL("http://192.168.0.100:8080/WSInsert/ws/Insertar/puestos/agregar");
=======
        URL url = new URL(WSINSERTAR_URL);
>>>>>>> b4f924053c3b59ec7d7af87494f01e99e53b4c57
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
<<<<<<< HEAD
        URL url = new URL("http://192.168.0.101:8080/WSUpdatBiblioteca/ws/Updates/Puesto" + "/" + puesto.getId_puesto());
=======
        URL url = new URL(WSUPDATE_URL + "/" + puesto.getId_puesto());
>>>>>>> b4f924053c3b59ec7d7af87494f01e99e53b4c57
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
