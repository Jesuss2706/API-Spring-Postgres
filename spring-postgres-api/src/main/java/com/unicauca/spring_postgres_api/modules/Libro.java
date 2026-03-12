package com.unicauca.spring_postgres_api.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "LIBRO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIB_ID", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "LIB_TITULO", nullable = false, length = 200)
    private String titulo;

    @NotNull
    @Column(name = "LIB_FECHA", nullable = false)
    private Integer fecha;

    @Size(max = 100)
    @Column(name = "LIB_GENERO", length = 100)
    private String genero;

    @NotNull
    @Column(name = "LIB_PAGINAS", nullable = false)
    private Integer paginas;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIB_PERSONA", nullable = false)
    private Persona persona;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIB_AUTOR", nullable = false)
    private Autor autor;
}