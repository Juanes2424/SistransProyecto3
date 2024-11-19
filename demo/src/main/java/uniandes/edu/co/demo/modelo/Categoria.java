package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "categorias")
@ToString
public class Categoria {
    @Id
    private String codigo;
    private String nombre;
    private String descripcion;
    private String caracteristicas_de_almacenamiento;

    public Categoria() {
        ;
    }

    public Categoria(String codigo, String nombre, String descripcion,
            String caracteristicas_almacenamiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicas_de_almacenamiento = caracteristicas_almacenamiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas_de_almacenamiento() {
        return caracteristicas_de_almacenamiento;
    }

    public void setCaracteristicas_de_almacenamiento(String caracteristicas_de_almacenamiento) {
        this.caracteristicas_de_almacenamiento = caracteristicas_de_almacenamiento;
    }

}
