package org.example;

import org.example.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("---COMENZANDO---");

        try {
            entityManager.getTransaction().begin();
            //Creación de objetos
            Factura factura1 = Factura.builder().numero(1).fecha("10/10/2002").build();
            Domicilio dom1 = Domicilio.builder().nombreCalle("Almirante Brown").numero(16).build();
            Cliente clien1 = Cliente.builder().apellido("Lemos").nombre("Ana").dni(44560780).domicilio(dom1).build();
            Categoria juguetes = Categoria.builder().denominacion("Juguetes").build();
            Categoria libreria = Categoria.builder().denominacion("Libreria").build();
            Categoria regalos = Categoria.builder().denominacion("Regalos").build();
            Articulo art1 = Articulo.builder().denominacion("Autito HotWheels").cantidad(20).precio(4500).build();
            Articulo art2 = Articulo.builder().denominacion("Cuaderno A4").cantidad(18).precio(2500).build();
            Articulo art3 = Articulo.builder().denominacion("Papel de regalo").cantidad(30).precio(1500).build();
            DetalleFactura det1 = DetalleFactura.builder().build();
            DetalleFactura det2 = DetalleFactura.builder().build();

            //Asignarle al domicilio el cliente
            dom1.setCliente(clien1);

            //Asignarle a la factura el cliente
            factura1.setCliente(clien1);

            //Asignarle las categorias a un articulo
            art1.getCategorias().add(juguetes);
            art2.getCategorias().add(libreria);
            art2.getCategorias().add(regalos);
            art3.getCategorias().add(libreria);
            art3.getCategorias().add(regalos);

            //Asignarle los artículos a una categoría
            juguetes.getArticulos().add(art1);
            libreria.getArticulos().add(art2);
            libreria.getArticulos().add(art3);
            regalos.getArticulos().add(art2);
            regalos.getArticulos().add(art3);

            //Armar detalles de facturas
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(det1.calcularSubtotal(art1.getPrecio(),2));
            det1.setFactura(factura1);
            det2.setArticulo(art2);
            det2.setCantidad(4);
            det2.setSubtotal(det2.calcularSubtotal(art2.getPrecio(),4));
            det2.setFactura(factura1);

            //Asignarle los detalles a los articulos y a la factura
            art1.getDetalleFacturas().add(det1);
            art2.getDetalleFacturas().add(det2);
            factura1.getDetalleFacturas().add(det1);
            factura1.getDetalleFacturas().add(det2);

            //Asignarle el total a la factura
            factura1.setTotal(det1.getSubtotal()+det2.getSubtotal());

            //Persistimos la factura
            entityManager.persist(factura1);


            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Ocurrió un error en la persistencia.");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}