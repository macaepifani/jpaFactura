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
import java.util.List;
import java.util.ArrayList;

//@Annotations
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity

public class Articulo implements Serializable {
    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Denominacion")
    private String denominacion;
    @Column(name = "Precio")
    private int precio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "articulo_categoria", joinColumns = @JoinColumn(name = "articulo_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @Default
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @OneToMany(mappedBy = "articulo")
    @Column(name = "Detalles de Facturas") //¿ESTO VA?
    @Default
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
}
