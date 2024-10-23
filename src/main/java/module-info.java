module co.edu.uniquindio.todotechfe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;


    opens co.edu.uniquindio.todotechfe.controllers to javafx.fxml;

    // Exporta el paquete que contiene el controlador
    exports co.edu.uniquindio.todotechfe.controllers;

    exports co.edu.uniquindio.todotechfe.dtos; // Asegúrate de exportar este paquete
    opens co.edu.uniquindio.todotechfe.dtos to com.fasterxml.jackson.databind;


    // Si usas otros paquetes, asegúrate de exportarlos también
    exports co.edu.uniquindio.todotechfe.app; // O el paquete que contenga tu clase principal
}
