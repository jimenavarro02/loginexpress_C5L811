package main.java.com.loginexpress.data;

import com.logiexpress.domain.Paquete;
import com.logiexpress.domain.EstadoPaquete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

    @Query("SELECT p FROM Paquete p WHERE p.estado = :estado")
    Page<Paquete> buscarPorEstado(@Param("estado") EstadoPaquete estado, Pageable pageable);
}