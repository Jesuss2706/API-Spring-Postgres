package com.unicauca.spring_postgres_api.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "DOMICILIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOM_ID", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "DOM_CALLE", nullable = false, length = 150)
    private String calle;

    @NotNull
    @Column(name = "DOM_NUMERO", nullable = false)
    private Integer numero;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOM_LOCALIDAD", nullable = false)
    private Localidad localidad;
}