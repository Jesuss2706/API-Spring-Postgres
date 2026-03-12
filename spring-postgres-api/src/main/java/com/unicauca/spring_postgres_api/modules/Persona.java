package com.unicauca.spring_postgres_api.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "PERSONA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "PER_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    @Column(name = "PER_APELLIDO", nullable = false, length = 100)
    private String apellido;

    @NotNull
    @Column(name = "PER_DNI", nullable = false, unique = true)
    private Integer dni;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PER_DOMICILIO")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Libro> libros;
}