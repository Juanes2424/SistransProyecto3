package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "productos")
@ToString
public class Producto {
    @Id
    private String codigo_barras;
    private String nombre;
    private Integer precio_unitario;
    private String presentacion;
    private Integer cantidad_presentacion;
    private String unidad_medida_presentacion;
    private Integer cantidad_empaque;
    private String unidad_empaque;
    private String fecha_expiracion;
    private Categoria categoria;

    public Producto() {
        ;
    }

    public Producto(String codigo_barras, String nombre, Integer precio_unitario, String presentacion,
            Integer cantidad_presentacion, String unidad_medida_presentacion, Integer cantidad_empaque,
            String unidad_empaque, String fecha_expiracion, Categoria categoria) {
        this.codigo_barras = codigo_barras;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
        this.presentacion = presentacion;
        this.cantidad_presentacion = cantidad_presentacion;
        this.unidad_medida_presentacion = unidad_medida_presentacion;
        this.cantidad_empaque = cantidad_empaque;
        this.unidad_empaque = unidad_empaque;
        this.fecha_expiracion = fecha_expiracion;
        this.categoria = categoria;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Integer precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getCantidad_presentacion() {
        return cantidad_presentacion;
    }

    public void setCantidad_presentacion(Integer cantidad_presentacion) {
        this.cantidad_presentacion = cantidad_presentacion;
    }

    public String getUnidad_medida_presentacion() {
        return unidad_medida_presentacion;
    }

    public void setUnidad_medida_presentacion(String unidad_medida_presentacion) {
        this.unidad_medida_presentacion = unidad_medida_presentacion;
    }

    public Integer getCantidad_empaque() {
        return cantidad_empaque;
    }

    public void setCantidad_empaque(Integer cantidad_empaque) {
        this.cantidad_empaque = cantidad_empaque;
    }

    public String getUnidad_empaque() {
        return unidad_empaque;
    }

    public void setUnidad_empaque(String unidad_empaque) {
        this.unidad_empaque = unidad_empaque;
    }

    public String getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
