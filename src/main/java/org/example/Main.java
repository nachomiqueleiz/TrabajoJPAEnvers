package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("EntityManager ha iniciado");

        // Persistir una nueva entidad Cliente
        entityManager.getTransaction().begin();

        Cliente cliente1 = Cliente.builder()
                .dni(45531756L)
                .apellido("Miqueleiz")
                .nombre("Ignacio")
                .build();

        Domicilio domi1 = Domicilio.builder()
                .nombreCalle("Volcan Santa Maria")
                .numero(1200)
                .build();

        Categoria categoria1 = Categoria.builder()
                .denominacion("Galletas")
                .build();

        Categoria categoria2 = Categoria.builder()
                .denominacion("Limpieza")
                .build();

        Categoria categoria3 = Categoria.builder()
                .denominacion("Almacen")
                .build();

        Articulo articulo1 = Articulo.builder()
                .cantidad(60)
                .denominacion("Oreo")
                .precio(300)
                .build();

        Articulo articulo2 = Articulo.builder()
                .cantidad(200)
                .denominacion("Lavandina")
                .precio(500)
                .build();

        Articulo articulo3 = Articulo.builder()
                .cantidad(100)
                .denominacion("Arroz")
                .precio(300)
                .build();

        DetalleFactura detalleFactura1 = DetalleFactura.builder()
                .cantidad(4)
                .subtotal(1200)
                .build();

        DetalleFactura detalleFactura2 = DetalleFactura.builder()
                .cantidad(3)
                .subtotal(1500)
                .build();

        DetalleFactura detalleFactura3 = DetalleFactura.builder()
                .cantidad(1)
                .subtotal(300)
                .build();

        Factura factura1 = Factura.builder()
                .fecha("04-09-2024")
                .numero(1)
                .total(3000)
                .build();

        domi1.setCliente(cliente1);

        cliente1.getFacturas().add(factura1);
        cliente1.setDomicilio(domi1);

        factura1.setCliente(cliente1);
        factura1.getDetalle().add(detalleFactura1);
        factura1.getDetalle().add(detalleFactura2);
        factura1.getDetalle().add(detalleFactura3);

        detalleFactura1.setFactura(factura1);
        detalleFactura1.setArticulo(articulo1);
        detalleFactura2.setFactura(factura1);
        detalleFactura2.setArticulo(articulo2);
        detalleFactura3.setFactura(factura1);
        detalleFactura3.setArticulo(articulo3);

        articulo1.getDetalle().add(detalleFactura1);
        articulo1.getCategorias().add(categoria1);
        articulo2.getDetalle().add(detalleFactura2);
        articulo2.getCategorias().add(categoria2);
        articulo3.getDetalle().add(detalleFactura3);
        articulo3.getCategorias().add(categoria3);

        categoria1.getArticulos().add(articulo1);
        categoria2.getArticulos().add(articulo2);
        categoria3.getArticulos().add(articulo3);


        entityManager.persist(factura1);
        entityManager.flush();
        entityManager.getTransaction().commit();

        //Edita el cliente
        entityManager.getTransaction().begin();
        cliente1.setNombre("Nacho");
        entityManager.getTransaction().commit();
        
        //Borrar el cliente
        entityManager.getTransaction().begin();
        entityManager.remove(cliente1);
        entityManager.getTransaction().commit();

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        System.out.println("EntityManager ha terminado");
        entityManagerFactory.close();
    }
}
