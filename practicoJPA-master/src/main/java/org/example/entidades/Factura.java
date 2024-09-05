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

public class Factura implements Serializable {
    @Column(name = "Fecha")
    private String fecha;
    @Column(name = "Numero")
    private int numero;
    @Column(name = "Total")
    private int total;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_Cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Default
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
}
