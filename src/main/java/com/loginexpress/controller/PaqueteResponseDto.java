package main.java.com.loginexpress.controller;

import com.logiexpress.domain.EstadoPaquete;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaqueteResponseDto {
    private Long id;
    private String codigoRastreo;
    private String descripcion;
    private Double pesoKg;
    private EstadoPaquete estado;
    private String nombreCliente;
}