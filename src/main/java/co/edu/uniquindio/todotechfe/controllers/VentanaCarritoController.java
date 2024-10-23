package co.edu.uniquindio.todotechfe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VentanaCarritoController {

    @FXML
    private ComboBox<?> clienteComboBox;

    @FXML
    private Label clienteSeleccionadoLabel;

    @FXML
    private VBox productosVBox;

    @FXML
    private Label totalLabel;

    @FXML
    void finalizarCompra(ActionEvent event) {

    }

}
