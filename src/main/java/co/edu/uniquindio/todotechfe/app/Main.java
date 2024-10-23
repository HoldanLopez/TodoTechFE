package co.edu.uniquindio.todotechfe.app;

import co.edu.uniquindio.todotechfe.controllers.VentanaIngresoController;
import co.edu.uniquindio.todotechfe.controllers.VentanaProductosController;
import co.edu.uniquindio.todotechfe.dtos.ProductoDto;

import java.util.List;

public class Main{
    public static void main(String[] args) {
        VentanaProductosController ventanaProductosController = new VentanaProductosController();

        List<ProductoDto> list = ventanaProductosController.obtenerProductos();

        list.forEach(System.out::println);

    }
}
