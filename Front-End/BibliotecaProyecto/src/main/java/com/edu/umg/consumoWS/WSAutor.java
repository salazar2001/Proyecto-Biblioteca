package com.edu.umg.consumoWS;

import com.edu.umg.entity.Autor;
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

public class WSAutor {
    //Agregar aca los Endpoints de cada WS
    private static final 
            String WSLISTAR_URL="http://192.168.0.100:8083/WSListar-1.0-SNAPSHOT/ws/listar/autores";
    private static final 
            String WSINSERTAR_URL="http://192.168.0.110:8082/WSInsertar-1.0-SNAPSHOT/ws/insertar/autor";
    private static final String 
            WSUPDATE_URL="http://192.168.0.104:8084/WSUpdate-1.0-SNAPSHOT/ws/actualizar/autor";
    // Obtener todos los autores
    public List<Autor> obtenerAutores() throws Exception {
        List<Autor> autores = new ArrayList<>();
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
            JSONObject jsonAutor = (JSONObject) obj;
            Autor autor = new Autor();
            autor.setId_autor(((Long) jsonAutor.get("id_autor")).intValue());
            autor.setNombre((String) jsonAutor.get("nombre"));
            autor.setApellido((String) jsonAutor.get("apellido"));
            autor.setObservaciones((String) jsonAutor.get("observaciones"));
            autor.setFecha_registro(DateUtil.dateFromString((String) jsonAutor.get("fecha_registro")));
            autores.add(autor);
        }

        return autores;
    }

    // Crear un nuevo autor
    public void crearAutor(Autor autor) throws Exception {
        URL url = new URL(WSINSERTAR_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String fechaRegistro = DateUtil.dateToString(new Date()); // Aquí se obtiene la fecha actual en formato String
        
        // Convertir el objeto Autor a JSON
        JSONObject jsonAutor = new JSONObject();
        jsonAutor.put("nombre", autor.getNombre());
        jsonAutor.put("apellido", autor.getApellido());
        jsonAutor.put("observaciones", autor.getObservaciones());
        jsonAutor.put("fecha_registro", fechaRegistro); // Convertir a String

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonAutor.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
    
    // Actualizar un autor existente
    public void actualizarAutor(Autor autor) throws Exception {
        URL url = new URL(WSUPDATE_URL + "/" + autor.getId_autor());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Convertir el objeto Autor a JSON
        JSONObject jsonAutor = new JSONObject();
        jsonAutor.put("nombre", autor.getNombre());
        jsonAutor.put("apellido", autor.getApellido());
        jsonAutor.put("observaciones", autor.getObservaciones());
        jsonAutor.put("fecha_registro", DateUtil.dateToString(autor.getFecha_registro())); // Convertir a String

        // Escribir el JSON en el cuerpo de la petición
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonAutor.toJSONString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
}
