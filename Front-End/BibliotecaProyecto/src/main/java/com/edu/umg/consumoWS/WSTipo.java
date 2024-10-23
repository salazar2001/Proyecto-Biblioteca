package com.edu.umg.consumoWS;

import com.edu.umg.entity.Tipo;
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

public class WSTipo {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private static final String WS_URL = "http://192.168.191.142:8080/WSBiblioteca/webresources/tipos";
=======
>>>>>>> e8cdb30 (Nuevo formato Json)
=======
    
        //Agregar aca los Endpoints de cada WS
    private static final 
            String WSLISTAR_URL="http://192.168.0.100:8083/WSListar-1.0-SNAPSHOT/ws/listar/tipos";
    private static final 
            String WSINSERTAR_URL="http://192.168.0.110:8082/WSInsertar-1.0-SNAPSHOT/ws/insertar/tipo";
    private static final 
            String WSUPDATE_URL="http://192.168.0.104:8084/WSUpdate-1.0-SNAPSHOT/ws/actualizar/tipo";
    
>>>>>>> dc1634f (Agregando CONSTANTES para mejor consumo de los WS)
=======
    
        //Agregar aca los Endpoints de cada WS
    private static final String WSLISTAR_URL=" ";
    private static final String WSINSERTAR_URL=" ";
    private static final String WSUPDATE_URL=" ";
    
>>>>>>> 978cd2794ddb61a19eb00465e3429a57e79b19e1

    // Obtener todos los tipos
    public List<Tipo> obtenerTipos() throws Exception {
        List<Tipo> tipos = new ArrayList<>();
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
            JSONObject jsonTipo = (JSONObject) obj;
            Tipo tipo = new Tipo();
            tipo.setId_tipo(((Long) jsonTipo.get("id_tipo")).intValue());
            tipo.setNombre((String) jsonTipo.get("nombre"));
            tipos.add(tipo);
        }

        return tipos;
    }

    // Crear un nuevo tipo
    public void crearTipo(Tipo tipo) throws Exception {
        URL url = new URL(WSINSERTAR_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Tipo a JSON
        JSONObject jsonTipo = new JSONObject();
        jsonTipo.put("nombre", tipo.getNombre());

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonTipo.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }

    // Actualizar un tipo existente
    public void actualizarTipo(Tipo tipo) throws Exception {
        URL url = new URL(WSUPDATE_URL + "/" + tipo.getId_tipo());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Tipo a JSON
        JSONObject jsonTipo = new JSONObject();
        jsonTipo.put("nombre", tipo.getNombre());

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonTipo.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
}
