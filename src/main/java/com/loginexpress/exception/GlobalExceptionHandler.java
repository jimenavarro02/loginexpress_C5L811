package main.java.com.loginexpress.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PesoExcedidoException.class)
    public ResponseEntity<ProblemDetailsDto> handlePesoExcedido(PesoExcedidoException ex, HttpServletRequest request) {
        ProblemDetailsDto problem = ProblemDetailsDto.builder()
                .type("https://api.logiexpress.com/errors/peso-excedido")
                .title("Peso Excedido")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(ex.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<ProblemDetailsDto> handleClienteNoEncontrado(ClienteNoEncontradoException ex, HttpServletRequest request) {
        ProblemDetailsDto problem = ProblemDetailsDto.builder()
                .type("https://api.logiexpress.com/errors/cliente-no-encontrado")
                .title("Cliente No Encontrado")
                .status(HttpStatus.NOT_FOUND.value())
                .detail(ex.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }
}