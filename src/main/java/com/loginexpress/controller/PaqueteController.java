package main.java.com.loginexpress.controller;

import com.logiexpress.business.PaqueteService;
import com.logiexpress.domain.EstadoPaquete;
import com.logiexpress.domain.Paquete;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/paquetes")
@RequiredArgsConstructor
@Tag(name = "Paquetes", description = "Endpoints para la gestión de paquetes LogiExpress")
public class PaqueteController {

    private final PaqueteService paqueteService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo paquete")
    @ApiResponse(responseCode = "201", description = "Paquete creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Peso excedido o datos inválidos")
    public ResponseEntity<PaqueteResponseDto> registrar(@Valid @RequestBody PaqueteRequestDto requestDto) {
        Paquete paquete = paqueteService.registrarPaquete(
                requestDto.getCodigoRastreo(),
                requestDto.getDescripcion(),
                requestDto.getPesoKg(),
                requestDto.getClienteCedula()
        );

        PaqueteResponseDto responseDto = PaqueteResponseDto.builder()
                .id(paquete.getId())
                .codigoRastreo(paquete.getCodigoRastreo())
                .descripcion(paquete.getDescripcion())
                .pesoKg(paquete.getPesoKg())
                .estado(paquete.getEstado())
                .nombreCliente(paquete.getCliente().getNombre())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    @Operation(summary = "Consultar paquetes por estado de manera paginada")
    @ApiResponse(responseCode = "200", description = "Lista paginada de paquetes")
    public ResponseEntity<Page<PaqueteResponseDto>> listarPorEstado(
            @RequestParam EstadoPaquete estado,
            Pageable pageable) {
        Page<Paquete> paquetes = paqueteService.listarPorEstado(estado, pageable);
        Page<PaqueteResponseDto> responseDtos = paquetes.map(p -> PaqueteResponseDto.builder()
                .id(p.getId())
                .codigoRastreo(p.getCodigoRastreo())
                .descripcion(p.getDescripcion())
                .pesoKg(p.getPesoKg())
                .estado(p.getEstado())
                .nombreCliente(p.getCliente().getNombre())
                .build());

        return ResponseEntity.ok(responseDtos);
    }
}