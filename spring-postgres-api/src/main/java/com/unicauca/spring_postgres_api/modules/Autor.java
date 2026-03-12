package com.unicauca.spring_postgres_api.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "AUTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUT_ID", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "AUT_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    @Column(name = "AUT_APELLIDO", nullable = false, length = 100)
    private String apellido;

    @Size(max = 1000)
    @Column(name = "AUT_BIOGRAFIA", length = 1000)
    private String biografia;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Libro> libros;
}