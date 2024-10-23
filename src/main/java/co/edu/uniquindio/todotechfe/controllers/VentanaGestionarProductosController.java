package co.edu.uniquindio.todotechfe.controllers;

import co.edu.uniquindio.todotechfe.constantes.Constantes;
import co.edu.uniquindio.todotechfe.dtos.ProductoDtoCrear;
import co.edu.uniquindio.todotechfe.dtos.ProductoDtoGet;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class VentanaGestionarProductosController {

    private final String host = Constantes.SERVIDOR;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Variable global para almacenar el producto seleccionado
    private ProductoDtoGet productoSeleccionado;

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCargarImagen;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<ProductoDtoGet, String> colCodigo;

    @FXML
    private TableColumn<ProductoDtoGet, String> colNombre;

    @FXML
    private TableColumn<ProductoDtoGet, String> colDescripcion;

    @FXML
    private TableColumn<ProductoDtoGet, Double> colPrecio;

    @FXML
    private TableColumn<ProductoDtoGet, Integer> colCantidad;

    @FXML
    private TableColumn<ProductoDtoGet, String> colIdBodega;

    @FXML
    private TableView<ProductoDtoGet> tablaProductos;

    @FXML
    private TextField txtCantidadProducto;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private TextArea txtDescripcionProducto;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtRutaImagenProducto;

    private ObservableList<ProductoDtoGet> listaproductos;

    @FXML
    public void initialize() {
        configurarColumnas();
        obtenerProductos();

        // Listener para seleccionar un producto
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                productoSeleccionado = newValue; // Guardar el producto seleccionado

                // Llenar los campos de texto
                txtCodigoProducto.setText(newValue.codigo());
                txtNombreProducto.setText(newValue.nombre());
                txtDescripcionProducto.setText(newValue.descripcion());
                txtPrecioProducto.setText(String.valueOf(newValue.precio()));
                txtCantidadProducto.setText(String.valueOf(newValue.cantidad()));
                txtIdProducto.setText(newValue.idBodega());
                txtRutaImagenProducto.setText(newValue.imagen());

                // Hacer los campos no editables
                setEditableFields(false);
            }
        });
    }

    private void configurarColumnas() {
        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        colPrecio.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().precio()).asObject());
        colCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().cantidad()).asObject());
        colIdBodega.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idBodega()));
    }

    public void obtenerProductos() {
        this.listaproductos = listarproductos();
        tablaProductos.setItems(listaproductos); // Vincular el ObservableList a la tabla
    }

    private ObservableList<ProductoDtoGet> listarproductos() {
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

                // Imprimir la respuesta para depuración
                String jsonResponse = response.toString();
                System.out.println("Respuesta JSON: " + jsonResponse);

                // Deserializar la respuesta JSON a un array de ProductoDtoGet
                ProductoDtoGet[] productosArray = objectMapper.readValue(jsonResponse, ProductoDtoGet[].class);
                // Crear un ObservableList a partir del array
                return FXCollections.observableArrayList(Arrays.asList(productosArray));
            } else {
                System.out.println("Error en la petición, código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(); // Retornar una lista vacía en caso de error
    }

    private void setEditableFields(boolean editable) {
        txtCodigoProducto.setEditable(editable);
        txtNombreProducto.setEditable(editable);
        txtDescripcionProducto.setEditable(editable);
        txtPrecioProducto.setEditable(editable);
        txtCantidadProducto.setEditable(editable);
        txtRutaImagenProducto.setEditable(editable);
        txtIdProducto.setEditable(editable);
    }

    @FXML
    void aniadirProducto(ActionEvent event) {
       crearProducto();
    }

    private void crearProducto(){
        String url = host + "/api/producto/crear";

        String codigo = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        String descripcion = txtDescripcionProducto.getText();
        double precio = Double.parseDouble(txtPrecioProducto.getText());
        int cantidad = Integer.parseInt(txtCantidadProducto.getText());
        String imagen = txtRutaImagenProducto.getText();
        String idBodega = txtIdProducto.getText(); // Asumiendo que este campo contiene el ID de la bodega

        // Crear el DTO
        ProductoDtoCrear productoDto = new ProductoDtoCrear(codigo, nombre, descripcion, precio, cantidad, imagen, idBodega);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Convertir el DTO a JSON
            String jsonInputString = objectMapper.writeValueAsString(productoDto);

            // Enviar la solicitud
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Producto creado exitosamente.");
                // Recargar la lista de productos
                obtenerProductos();
            } else {
                System.out.println("Error al crear el producto, código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void borrarProducto(ActionEvent event) {
        if (productoSeleccionado != null) {
            borrarProducto();
        } else {
            System.out.println("No se ha seleccionado ningún producto para eliminar.");
        }
    }

    private void borrarProducto() {
        String url = host + "/api/producto/eliminar/" + productoSeleccionado.codigo();

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Producto eliminado exitosamente.");
                // Recargar la lista de productos
                obtenerProductos();
            } else {
                System.out.println("Error al eliminar el producto, código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cancelarSeleccion(ActionEvent event) {
        // Implementa la lógica para cancelar la selección
    }

    @FXML
    void editarProducto(ActionEvent event) {
        // Habilitar los campos de texto para edición
        setEditableFields(true);
    }

    @FXML
    void actualizarProducto(ActionEvent event) {
        if (productoSeleccionado != null) {
            actualizarProducto();
        } else {
            System.out.println("No se ha seleccionado ningún producto para actualizar.");
        }
    }

    private void actualizarProducto() {
        String url = host + "/api/producto/actualizar/" + productoSeleccionado.codigo();

        String codigo = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        String descripcion = txtDescripcionProducto.getText();
        double precio = Double.parseDouble(txtPrecioProducto.getText());
        int cantidad = Integer.parseInt(txtCantidadProducto.getText());
        String imagen = txtRutaImagenProducto.getText();
        String idBodega = txtIdProducto.getText(); // Asumiendo que este campo contiene el ID de la bodega

        // Crear el DTO
        ProductoDtoCrear productoDto = new ProductoDtoCrear(codigo, nombre, descripcion, precio, cantidad, imagen, idBodega);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Convertir el DTO a JSON
            String jsonInputString = objectMapper.writeValueAsString(productoDto);

            // Enviar la solicitud
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Producto actualizado exitosamente.");
                // Recargar la lista de productos
                obtenerProductos();
            } else {
                System.out.println("Error al actualizar el producto, código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cargarImagen(ActionEvent event){
        openFileChooser();
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Todos los Archivos", "*.*")
        );

        // Obtener el Stage actual
        Stage stage = (Stage) txtRutaImagenProducto.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Obtener la ruta del archivo
            String filePath = selectedFile.getAbsolutePath();
            txtRutaImagenProducto.setText(filePath); // Establecer la ruta en el campo de texto
            System.out.println("Archivo seleccionado: " + filePath);
        }
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
