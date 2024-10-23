package co.edu.uniquindio.todotechfe.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaInicioController {

    @FXML
    private ImageView btnBodegas;

    @FXML
    private ImageView btnCarrito;

    @FXML
    private ImageView btnCerrarSesion;

    @FXML
    private ImageView btnClientes;

    @FXML
    private ImageView btnEnvios;

    @FXML
    private ImageView btnGestionProductos;

    @FXML
    private ImageView btnPQRS;

    @FXML
    private ImageView btnProductos;

    @FXML
    void abrirVentanaBodegas(MouseEvent event) throws IOException {

    }

    @FXML
    void abrirVentanaCarrito(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaCarrito.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Carrito");
        newStage.show();
    }

    @FXML
    void abrirVentanaClientes(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaClientes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Clientes");
        currentStage.close();
        newStage.show();
    }

    @FXML
    void abrirVentanaEnvios(MouseEvent event) {

    }

    @FXML
    void abrirVentanaGestionarProductos(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaGestionarProductos.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Gestion de Productos");
        currentStage.close();
        newStage.show();
    }

    @FXML
    void abrirVentanaProductos(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaProductos.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Productos");
        currentStage.close();
        newStage.show();
    }

    @FXML
    void cerrarSesion(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaIngreso.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Login");
        currentStage.close();
        newStage.show();
    }

}
