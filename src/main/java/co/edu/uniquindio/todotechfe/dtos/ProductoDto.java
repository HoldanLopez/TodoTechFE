package co.edu.uniquindio.todotechfe.dtos;

public record ProductoDto(
        String codigo,
        String nombre,
        String descripcion,
        double precio,
        int cantidad,
        String imagen
) {
}
