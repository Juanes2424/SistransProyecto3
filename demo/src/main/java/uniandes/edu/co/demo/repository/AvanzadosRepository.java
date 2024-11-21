package uniandes.edu.co.demo.repository;

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

                new Document("$match", new Document("_id", sucursal_id) // Filter by document _id
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

    public List<Document> inventario() {

        List<Document> pipeline = List.of();

        return pipeline;
    }
}
