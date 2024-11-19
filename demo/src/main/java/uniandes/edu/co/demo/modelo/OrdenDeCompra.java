package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "ordenesdecompra")
@ToString
public class OrdenDeCompra {
    @Id
    private int id;
    private Sucursal sucursal;
    private Proveedor proveedor;
    private String estado;
    private String fecha_entrega;
    private String fecha_creacion;

    public OrdenDeCompra() {
        ;
    }

    public OrdenDeCompra(int id, Sucursal sucursal, Proveedor proveedor, String estado, String fecha_entrega,
            String fecha_creacion) {
        this.id = id;
        this.sucursal = sucursal;
        this.proveedor = proveedor;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_entrega = fecha_entrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}
