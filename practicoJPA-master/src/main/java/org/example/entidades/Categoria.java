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

public class Categoria implements Serializable {
    @Column(name = "Denominacion")
    private String denominacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany (mappedBy = "categorias")
    @Default
    private List<Articulo> articulos = new ArrayList<Articulo>();
}
