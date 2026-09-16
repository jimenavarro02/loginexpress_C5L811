package main.java.com.loginexpress.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaqueteRequestDto {
    @NotBlank(message = "El código de rastreo es obligatorio")
    private String codigoRastreo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a cero")
    private Double pesoKg;

    @NotBlank(message = "La cédula del cliente es obligatoria")
    private String clienteCedula;
}