package org.example.entidades;
//Importaciones
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.io.Serializable;

//@Annotations
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity

public class DetalleFactura implements Serializable {
    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Subtotal")
    private int subtotal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_Articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_Factura")
    private Factura factura;

    /*MÉTODO PROPIO PARA CALCUCAR EL SUBTOTAL*/
    public int calcularSubtotal(int precioArt, int cantidad) {
        return precioArt * cantidad;
    }
}
