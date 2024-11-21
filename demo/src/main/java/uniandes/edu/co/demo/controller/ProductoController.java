package uniandes.edu.co.demo.controller;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Producto;
import uniandes.edu.co.demo.repository.AvanzadosRepository;
import uniandes.edu.co.demo.repository.ProductoRepository;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AvanzadosRepository avanzadosRepository;

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

    // RFC1
    @GetMapping("/rfc1")
    public ResponseEntity<List<Document>> rfc1(@RequestBody Map<String, Object> params) {
        try {
            Integer precio_inferior = Integer.parseInt(params.get("precio_inferior").toString());
            Integer precio_superior = Integer.parseInt(params.get("precio_superior").toString());

            String fecha_inferior = params.get("fecha_inferior").toString();
            String fecha_superior = params.get("fecha_superior").toString();

            int sucursal_id = Integer.parseInt(params.get("sucursal_id").toString());
            String categoria_id = params.get("categoria_id").toString();

            List<Document> resultado = avanzadosRepository.productosConCaracteristica(precio_inferior, precio_superior,
                    fecha_inferior, fecha_superior, sucursal_id, categoria_id);

            // Retornar el resultado de la consulta
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // RFC2
    @GetMapping("/rfc2/{id}")
    public ResponseEntity<List<Document>> rfc2(@PathVariable("id") int id) {
        try {
            List<Document> resultado = avanzadosRepository.inventario();

            // Retornar el resultado de la consulta
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}