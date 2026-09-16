package main.java.com.loginexpress.business;

import com.logiexpress.data.ClienteRepository;
import com.logiexpress.data.PaqueteRepository;
import com.logiexpress.domain.Cliente;
import com.logiexpress.domain.EstadoPaquete;
import com.logiexpress.domain.Paquete;
import com.logiexpress.exception.ClienteNoEncontradoException;
import com.logiexpress.exception.PesoExcedidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Paquete registrarPaquete(String codigoRastreo, String descripcion, Double pesoKg, String clienteCedula) {
        if (pesoKg > 30.0) {
            throw new PesoExcedidoException("El peso del paquete (" + pesoKg + " kg) excede el límite permitido de 30.0 kg.");
        }

        Cliente cliente = clienteRepository.findByCedula(clienteCedula)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se encontró un cliente registrado con la cédula: " + clienteCedula));

        Paquete paquete = Paquete.builder()
                .codigoRastreo(codigoRastreo)
                .descripcion(descripcion)
                .pesoKg(pesoKg)
                .estado(EstadoPaquete.REGISTRADO)
                .cliente(cliente)
                .build();

        return paqueteRepository.save(paquete);
    }

    @Transactional(readOnly = true)
    public Page<Paquete> listarPorEstado(EstadoPaquete estado, Pageable pageable) {
        return paqueteRepository.buscarPorEstado(estado, pageable);
    }
}