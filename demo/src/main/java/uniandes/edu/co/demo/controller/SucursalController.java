package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Sucursal;
import uniandes.edu.co.demo.repository.SucursalRepository;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    // Crear una nueva sucursal
    @PostMapping("/new")
    public ResponseEntity<String> crearSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.insertarSucursal(sucursal);
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            ;
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
