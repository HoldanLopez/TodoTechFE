package co.edu.uniquindio.todotechfe.dtos;

public record ProductoDtoCrear(
        String codigo,
        String nombre,
        String descripcion,
        double precio,
        int cantidad,
        String imagen,
        String codigoBodega
) {
}
