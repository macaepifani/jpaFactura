package org.example.entidades;
//Importaciones
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;

//@Annotations
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity

public class Domicilio implements Serializable {
    @Column(name = "Nombre_Calle")
    private String nombreCalle;
    @Column(name = "Numero")
    private int numero;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;
}
