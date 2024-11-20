package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Categoria;
import uniandes.edu.co.demo.modelo.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    default void insertarProducto(Producto producto) {
        save(producto);
    }

    // Consultar producto por su ID
    @Query("{codigo_barras: ?0}")
    List<Producto> buscarPorId(String codigo);

    // Eliminar un producto por su ID
    @Query(value = "{codigo_barras: ?0}", delete = true)
    void eliminarProductoPorId(String codigo_barras);

    @Query("{codigo_barras: ?0}")
    @Update("{ $set: {codigo_barras: ?0, nombre: ?1, precio_unitario: ?2, presentacion: ?3, cantidad_presentacion: ?4, unidad_medida_presentacion: ?5, cantidad_empaque: ?6, unidad_empaque: ?7, fecha_expiracion: ?8, categoria: ?9}}")
    void actualizarProveedor(String codigo_barras, String nombre, Integer precio_unitario, String presentacion,
            Integer cantidad_presentacion, String unidad_medida_presentacion, Integer cantidad_empaque,
            String unidad_empaque, String fecha_expiracion, Categoria categoria);
}