package co.edu.uniquindio.todotechfe.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/todotechfe/VentanaIngreso.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

        // Configurar el escenario (ventana)
        primaryStage.setTitle("Ventana de Ingreso");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lanzar la aplicación JavaFX
        launch(args);
    }
}
