package co.edu.uniquindio.todotechfe.controllers;

import co.edu.uniquindio.todotechfe.constantes.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VentanaIngresoController {
    private final String host = Constantes.SERVIDOR;
    public TextField txtCorreo;
    public TextField txtPassword;
    public Button btnLogin;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void loguear(ActionEvent actionEvent) {
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();
        if (correo != null && password != null) {
            loguin(correo, password, actionEvent);
        }
    }

    private void loguin(String correo, String password, ActionEvent event) {
        Map<String, String> json = crearJson(correo, password);
        String jsonResponse = enviarSolicitud(json, event);

        if (jsonResponse != null) {
            System.out.println(jsonResponse);
        }
    }

    private Map<String, String> crearJson(String correo, String password) {
        Map<String, String> json = new HashMap<>();
        json.put("correo", correo);
        json.put("password", password);
        return json;
    }

    private String enviarSolicitud(Map<String, String> json, ActionEvent event) {
        try {
            URL url = new URL(host + "/api/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Convertir el mapa a JSON
            String jsonInputString = objectMapper.writeValueAsString(json);
            escribirCuerpoSolicitud(conn, jsonInputString);

            // Obtener la respuesta
            return leerRespuesta(conn, event);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void escribirCuerpoSolicitud(HttpURLConnection conn, String jsonInputString) throws Exception {
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    private String leerRespuesta(HttpURLConnection conn, ActionEvent event) throws Exception {
        int responseCode = conn.getResponseCode();
        System.out.println("Código de respuesta: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            abrirVentanaInicio(event);
            return response.toString();
        } else {
            System.out.println("Error en la petición");
            return null;
        }
    }

    private void abrirVentanaInicio(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaInicio.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Inicio");
        currentStage.close();
        newStage.show();
    }
}
