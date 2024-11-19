package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Bodega;

public interface BodegaRepository extends MongoRepository<Bodega, Integer> {

    default void insertarBodega(Bodega bodega) {
        save(bodega);
    }

    // Eliminar una bodega por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarBodegaPorId(int id);
}