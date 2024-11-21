package uniandes.edu.co.demo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.bson.Document;

@Repository
public class AvanzadosRepository {

    private final MongoTemplate mongoTemplate;

    public AvanzadosRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> productosConCaracteristica(int precio_inferior, int precio_superior, String fecha_inferior,
            String fecha_superior, int sucursal_id, String categoria_id) {

        List<Document> pipeline = List.of(
                new Document("$unwind", "$bodegas"),

                new Document("$lookup", new Document()
                        .append("from", "bodegas")
                        .append("localField", "bodegas")
                        .append("foreignField", "_id")
                        .append("as", "bodega_detalle")),

                new Document("$unwind", "$bodega_detalle"),

                new Document("$unwind", "$bodega_detalle.productos"),

                new Document("$lookup", new Document()
                        .append("from", "productos")
                        .append("localField", "productoID")
                        .append("foreignField", "codigo_barras")
                        .append("as", "pr")),

                new Document("$match", new Document("_id", sucursal_id)
                        .append("pr", new Document("$elemMatch", new Document("categoria._id", categoria_id)))),
                new Document("$project", new Document("pr", new Document("$filter", new Document()
                        .append("input", "$pr")
                        .append("as", "product")
                        .append("cond", new Document("$and", List.of(
                                new Document("$eq", Arrays.asList("$$product.categoria._id", categoria_id)),
                                new Document("$gte", Arrays.asList("$$product.fecha_expiracion", fecha_inferior)),
                                new Document("$lte", Arrays.asList("$$product.fecha_expiracion", fecha_superior)),
                                new Document("$gte", Arrays.asList("$$product.precio_unitario", precio_inferior)),
                                new Document("$lte", Arrays.asList("$$product.precio_unitario", precio_superior)))))))
                        .append("_id", 1)
                        .append("direccion", 1)
                        .append("telefono", 1)),

                new Document("$unwind", "$pr"),

                new Document("$group", new Document()
                        .append("_id", "$pr._id")
                        .append("product", new Document("$first", "$pr"))),

                new Document("$replaceRoot", new Document("newRoot", "$product"))

        );

        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new java.util.ArrayList<>());
    }

    public List<Document> inventario(int id) {

        // Se ingresa la sucursal para la cual se quiere el reporte. El reporte arroja,
        // para cada bodega de la sucursal
        // ingresada, el nombre de los productos que contiene, su cantidad actual
        // disponible, la cantidad mínima que
        // se requiere en inventario para esa bodega y su costo promedio.

        List<Document> pipeline = List.of(

                new Document("$unwind", "$bodegas"),

                new Document("$lookup", new Document()
                        .append("from", "bodegas")
                        .append("localField", "bodegas")
                        .append("foreignField", "_id")
                        .append("as", "bodega_detalle")),

                new Document("$unwind", "$bodega_detalle"),
                new Document("$unwind", "$bodega_detalle.productos"),

                new Document("$lookup", new Document()
                        .append("from", "productosenbodega")
                        .append("localField", "bodega_detalle.productos")
                        .append("foreignField", "_id")
                        .append("as", "inventario_detalle")),

                new Document("$lookup", new Document()
                        .append("from", "productos")
                        .append("localField", "inventario_detalle.productoID")
                        .append("foreignField", "_id")
                        .append("as", "producto_detalle")),

                new Document("$match", new Document()
                        .append("inventario_detalle", new Document("$ne", new ArrayList<>()))
                        .append("producto_detalle", new Document("$ne", new ArrayList<>()))),

                new Document("$addFields", new Document()
                        .append("detalle", new Document()
                                .append("producto_nombre",
                                        new Document("$arrayElemAt", Arrays.asList("$producto_detalle.nombre", 0)))
                                .append("inventario_nivel_minimo_reorden",
                                        new Document("$arrayElemAt",
                                                Arrays.asList("$inventario_detalle.nivel_minimo_reorden", 0)))
                                .append("inventario_total_existencias",
                                        new Document("$arrayElemAt",
                                                Arrays.asList("$inventario_detalle.total_existencias", 0)))
                                .append("inventario_costo_promedio",
                                        new Document("$arrayElemAt",
                                                Arrays.asList("$inventario_detalle.costo_promedio", 0))))),

                new Document("$project", new Document()
                        .append("_id", 1)
                        .append("detalle", 1)),

                new Document("$group", new Document()
                        .append("_id", "$_id")
                        .append("productos", new Document("$push", "$detalle")))

        );

        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new java.util.ArrayList<>());
    }
}
