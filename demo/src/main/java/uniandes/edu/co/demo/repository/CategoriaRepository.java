package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    default void insertarCategoria(Categoria categoria) {
        save(categoria);
    }

    // Consultar categoria por su ID
    @Query("{codigo: ?0}")
    List<Categoria> buscarPorId(String codigo);
}