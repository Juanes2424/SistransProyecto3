package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "bodegas")
@ToString
public class Bodega {
    @Id
    private int id;
    private String nombre;
    private int tamano_metros;
    private List<ProductoEnBodega> productos;

    public Bodega() {
        ;
    }

    public Bodega(int id, String nombre, int tamano_metros, List<ProductoEnBodega> productos) {
        this.id = id;
        this.nombre = nombre;
        this.tamano_metros = tamano_metros;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano_metros() {
        return tamano_metros;
    }

    public void setTamano_metros(int tamano_metros) {
        this.tamano_metros = tamano_metros;
    }

    public List<ProductoEnBodega> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoEnBodega> productos) {
        this.productos = productos;
    }

}
