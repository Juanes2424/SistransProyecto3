package uniandes.edu.co.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.OrdenDeCompra;
import uniandes.edu.co.demo.repository.OrdenDeCompraRepository;

@RestController
@RequestMapping("/ordendecompra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    // Crear una nueva orden de compra
    @PostMapping("/new")
    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            ordenDeCompra.setEstado("Vigente");
            ordenDeCompraRepository.insertarOrdenDeCompra(ordenDeCompra);
            return new ResponseEntity<>("OrdenDeCompra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            ;
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una orden de compra por codigo
    @GetMapping("/{id}")
    public ResponseEntity<List<OrdenDeCompra>> obtenerCategoriaPorId(@PathVariable("id") int codigo) {
        try {
            List<OrdenDeCompra> ordenes = ordenDeCompraRepository.buscarPorId(codigo);
            if (ordenes != null && !ordenes.isEmpty()) {
                return ResponseEntity.ok(ordenes);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}