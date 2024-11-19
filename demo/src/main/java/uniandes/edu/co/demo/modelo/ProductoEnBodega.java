package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "productosenbodega")
@ToString
public class ProductoEnBodega {
    @Id
    private String productoID;
    private int nivel_minimo_reorden;
    private int capacidad_almacenamiento;
    private double costo_bodega;
    private int total_existencias;
    private double costo_promedio;

    public ProductoEnBodega() {
        ;
    }

    public ProductoEnBodega(String productoID, int nivel_minimo_reorden, int capacidad_almacenamiento,
            double costo_bodega, int total_existencias, double costo_promedio) {
        this.productoID = productoID;
        this.nivel_minimo_reorden = nivel_minimo_reorden;
        this.capacidad_almacenamiento = capacidad_almacenamiento;
        this.costo_bodega = costo_bodega;
        this.total_existencias = total_existencias;
        this.costo_promedio = costo_promedio;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
    }

    public int getNivel_minimo_reorden() {
        return nivel_minimo_reorden;
    }

    public void setNivel_minimo_reorden(int nivel_minimo_reorden) {
        this.nivel_minimo_reorden = nivel_minimo_reorden;
    }

    public int getCapacidad_almacenamiento() {
        return capacidad_almacenamiento;
    }

    public void setCapacidad_almacenamiento(int capacidad_almacenamiento) {
        this.capacidad_almacenamiento = capacidad_almacenamiento;
    }

    public double getCosto_bodega() {
        return costo_bodega;
    }

    public void setCosto_bodega(double costo_bodega) {
        this.costo_bodega = costo_bodega;
    }

    public int getTotal_existencias() {
        return total_existencias;
    }

    public void setTotal_existencias(int total_existencias) {
        this.total_existencias = total_existencias;
    }

    public double getCosto_promedio() {
        return costo_promedio;
    }

    public void setCosto_promedio(double costo_promedio) {
        this.costo_promedio = costo_promedio;
    }

}
