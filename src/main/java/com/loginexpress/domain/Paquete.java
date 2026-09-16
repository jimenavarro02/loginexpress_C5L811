package main.java.com.loginexpress.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paquetes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoRastreo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double pesoKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPaquete estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}