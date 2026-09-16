package test.java.com.loginexpress.business;

import com.logiexpress.data.ClienteRepository;
import com.logiexpress.data.PaqueteRepository;
import com.logiexpress.domain.Cliente;
import com.logiexpress.domain.Paquete;
import com.logiexpress.domain.EstadoPaquete;
import com.logiexpress.exception.PesoExcedidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PaqueteService paqueteService;

    private Cliente clienteMock;

    @BeforeEach
    void setUp() {
        clienteMock = Cliente.builder()
                .id(1L)
                .cedula("123456789")
                .nombre("Juan Pérez")
                .correo("juan@correo.com")
                .build();
    }

    @Test
    void registrarPaquete_Exitoso() {
        when(clienteRepository.findByCedula("123456789")).thenReturn(Optional.of(clienteMock));
        when(paqueteRepository.save(any(Paquete.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Paquete resultado = paqueteService.registrarPaquete("TR-001", "Documentos", 15.0, "123456789");

        assertNotNull(resultado);
        assertEquals("TR-001", resultado.getCodigoRastreo());
        assertEquals(15.0, resultado.getPesoKg());
        assertEquals(EstadoPaquete.REGISTRADO, resultado.getEstado());
        verify(paqueteRepository, times(1)).save(any(Paquete.class));
    }

    @Test
    void registrarPaquete_LanzaPesoExcedidoException() {
        PesoExcedidoException exception = assertThrows(PesoExcedidoException.class, () -> {
            paqueteService.registrarPaquete("TR-002", "Caja Pesada", 35.0, "123456789");
        });

        assertTrue(exception.getMessage().contains("excede el límite permitido"));
        verify(paqueteRepository, never());
    }
}