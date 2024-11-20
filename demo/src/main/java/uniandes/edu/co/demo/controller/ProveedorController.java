package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Proveedor;
import uniandes.edu.co.demo.repository.ProveedorRepository;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear un nuevo proveedor
    @PostMapping("/new")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.insertarProveedor(proveedor);
            return new ResponseEntity<>("Proveedor creado exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            ;
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un proveedor existente
    @PostMapping("/{id}/edit")
    public ResponseEntity<String> actualizarProveedor(@PathVariable("id") String id, @RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.actualizarProveedor(
                    id, proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombre_contacto(),
                    proveedor.getTelefono_contacto());
            return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}