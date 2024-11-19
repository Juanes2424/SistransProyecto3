package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Bodega;
import uniandes.edu.co.demo.repository.BodegaRepository;

@RestController
@RequestMapping("/bodega")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Crear una nueva bodega
    @PostMapping("/new")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            bodegaRepository.insertarBodega(bodega);
            return new ResponseEntity<>("Bodega creada exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            ;
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una bodega por id
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarBodega(@PathVariable("id") int id) {
        try {
            bodegaRepository.eliminarBodegaPorId(id);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}