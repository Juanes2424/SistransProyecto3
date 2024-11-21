package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "sucursales")
@ToString
public class Sucursal {
    @Id
    private int id;
    private String nombre;
    private int tamano_metros;
    private String direccion;
    private String telefono;
    private String ciudad;
    private List<Integer> bodegas;

    public Sucursal() {
        ;
    }

    public Sucursal(int id, String nombre, int tamano_metros, String direccion, String telefono, String ciudad,
            List<Integer> bodegas) {
        this.id = id;
        this.nombre = nombre;
        this.tamano_metros = tamano_metros;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.bodegas = bodegas;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Integer> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Integer> bodegas) {
        this.bodegas = bodegas;
    }

}
