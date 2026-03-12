package com.unicauca.spring_postgres_api.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "LOCALIDAD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOC_ID", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "LOC_DENOMINACION", nullable = false, length = 150)
    private String denominacion;

    @OneToMany(mappedBy = "localidad")
    @JsonIgnore
    private List<Domicilio> domicilios;
}