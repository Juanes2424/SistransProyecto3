package uniandes.edu.co.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Producto;
import uniandes.edu.co.demo.repository.ProductoRepository;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo producto
    @PostMapping("/new")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        try {
            productoRepository.insertarProducto(producto);
            return new ResponseEntity<>("Producto creado exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            ;
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un producto por id
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") String id) {
        try {
            productoRepository.eliminarProductoPorId(id);
            return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un producto existente
    @PostMapping("/{id}/edit")
    public ResponseEntity<String> actualizarProducto(@PathVariable("id") String id, @RequestBody Producto producto) {
        try {
            productoRepository.actualizarProveedor(producto.getCodigo_barras(), producto.getNombre(),
                    producto.getPrecio_unitario(), producto.getPresentacion(), producto.getCantidad_presentacion(),
                    producto.getUnidad_medida_presentacion(), producto.getCantidad_empaque(),
                    producto.getUnidad_empaque(), producto.getFecha_expiracion(), producto.getCategoria());
            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un producto por codigo
    @GetMapping("/{id}")
    public ResponseEntity<List<Producto>> obtenerProductoPorId(@PathVariable("id") String codigo) {
        try {
            List<Producto> productos = productoRepository.buscarPorId(codigo);
            if (productos != null && !productos.isEmpty()) {
                return ResponseEntity.ok(productos);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}