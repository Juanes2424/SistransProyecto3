package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.OrdenDeCompra;

public interface OrdenDeCompraRepository extends MongoRepository<OrdenDeCompra, Integer> {

    default void insertarOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
        save(ordenDeCompra);
    }

    // Consultar OrdenDeCompra por su ID
    @Query("{_id: ?0}")
    List<OrdenDeCompra> buscarPorId(int codigo);
}