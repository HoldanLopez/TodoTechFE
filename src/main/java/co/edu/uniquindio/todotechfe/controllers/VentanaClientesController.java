package co.edu.uniquindio.todotechfe.controllers;

import co.edu.uniquindio.todotechfe.constantes.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaClientesController {

    private final String host = Constantes.SERVIDOR;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    private Button btnAniadirCliente;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField campoApellido;

    @FXML
    private TextField campoCedula;

    @FXML
    private TextField campoDireccion;

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoTelefono;

    @FXML
    private TableColumn<?, ?> columnaApellido;

    @FXML
    private TableColumn<?, ?> columnaCedula;

    @FXML
    private TableColumn<?, ?> columnaCorreo;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    private TableColumn<?, ?> columnaTelefono;

    @FXML
    private TableView<?> tablaClientes;

    @FXML
    void abrirVentaInicio(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaInicio.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Inicio");
        currentStage.close();
        newStage.show();
        newStage.isResizable();
    }

    @FXML
    void addCliente(ActionEvent event) {

    }

    @FXML
    void cancelarEdicion(ActionEvent event) {

    }

    @FXML
    void guardarCliente(ActionEvent event) {

    }

}
