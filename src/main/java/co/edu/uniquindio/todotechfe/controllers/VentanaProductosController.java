package co.edu.uniquindio.todotechfe.controllers;

import co.edu.uniquindio.todotechfe.constantes.Constantes;
import co.edu.uniquindio.todotechfe.dtos.ProductoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class VentanaProductosController {

    private final String host = Constantes.SERVIDOR;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    private Button btnAniadirCarrito;

    @FXML
    private Button btnRegresar;

    @FXML
    private FlowPane galeriaProductos;

    @FXML
    private ImageView imgSeleccionada;

    @FXML
    private Spinner<?> selectorCantidad;

    @FXML
    private TextField selectorDescuento;

    @FXML
    private Text txtCantidad;

    @FXML
    private Text txtCodigo;

    @FXML
    private Text txtDescripcion;

    @FXML
    private Text txtIdBodega;

    @FXML
    private Text txtNombre;

    @FXML
    private Text txtPrecio;

    @FXML
    void aniadirCarrito(ActionEvent event) {
        // Lógica para añadir al carrito
    }
    @FXML
    public void initialize(){
        obtenerProductos();
    }
    public List<ProductoDto> obtenerProductos() {
        String url = host + "/api/producto/listar";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Convertir la respuesta JSON a una lista de ProductoDto
                ProductoDto[] productosArray = objectMapper.readValue(response.toString(), ProductoDto[].class);
                return Arrays.asList(productosArray);
            } else {
                System.out.println("Error en la petición, código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Devuelve null si hay un error
    }

    public void mostrarProductos() {
        List<ProductoDto> productos = obtenerProductos();
        if (productos != null) {
            for (ProductoDto producto : productos) {
                // Crear elementos para mostrar en la interfaz
                crearElementoProducto(producto);
            }
        }
    }

    private void crearElementoProducto(ProductoDto producto) {
        // Crear un contenedor para el producto (puedes personalizarlo como necesites)
        FlowPane contenedorProducto = new FlowPane();

        // Crear imagen del producto
        ImageView imagenProducto = new ImageView();
        imagenProducto.setImage(new Image(producto.imagen()));
        imagenProducto.setFitHeight(100);
        imagenProducto.setFitWidth(100);

        // Crear texto con el nombre del producto
        Text nombreProducto = new Text(producto.nombre());
        Text precioProducto = new Text(String.valueOf(producto.precio()));

        // Añadir los elementos al contenedor
        contenedorProducto.getChildren().addAll(imagenProducto, nombreProducto, precioProducto);

        // Añadir el contenedor de producto a la galería
        galeriaProductos.getChildren().add(contenedorProducto);
    }

    @FXML
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
