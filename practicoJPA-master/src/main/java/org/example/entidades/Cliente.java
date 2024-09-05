package org.example.entidades;
//Importaciones
import lombok.Builder.Default;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Annotations
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity

public class Cliente implements Serializable {
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "DNI")
    private int dni;
    @Column(name = "Nombre")
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "cliente")
    @Column(name = "Factura")
    @Default
    private List<Factura> facturas = new ArrayList<Factura>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Domicilio")
    private Domicilio domicilio;
}
