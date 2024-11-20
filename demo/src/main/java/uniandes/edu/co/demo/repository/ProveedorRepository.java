package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    default void insertarProveedor(Proveedor proveedor) {
        save(proveedor);
    }

    @Query("{nit: ?0}")
    @Update("{ $set: {nit: ?0, nombre: ?1, direccion: ?2, nombre_contacto: ?3, telefono_contacto: ?4}}")
    void actualizarProveedor(String nit, String nombre, String direccion, String nombre_contacto,
            String telefono_contacto);
}