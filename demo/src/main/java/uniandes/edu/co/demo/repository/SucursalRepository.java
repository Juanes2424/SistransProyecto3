package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.demo.modelo.Sucursal;

public interface SucursalRepository extends MongoRepository<Sucursal, Integer> {

    default void insertarSucursal(Sucursal sucursal) {
        save(sucursal);
    }
}