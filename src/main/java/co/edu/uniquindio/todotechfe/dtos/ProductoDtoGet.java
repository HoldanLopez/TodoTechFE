package co.edu.uniquindio.todotechfe.dtos;

public record ProductoDtoGet(
        String codigo,
        String nombre,
        String descripcion,
        double precio,
        int cantidad,
        String imagen,
        String idBodega
) {
}
