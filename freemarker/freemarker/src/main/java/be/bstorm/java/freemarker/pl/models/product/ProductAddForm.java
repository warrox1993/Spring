package be.bstorm.java.freemarker.pl.models.product;

import be.bstorm.java.freemarker.dal.entities.ProductEntity;

public record ProductAddForm(
        String name,
        String description,
        double price
) {

    public ProductEntity toEntity() {
        ProductEntity entity = new ProductEntity();

        entity.setName(name());
        entity.setDescription(description());
        entity.setPrice(price());
        entity.setActive(true);

        return entity;
    }

    public static ProductAddForm fromEntity(ProductEntity entity) {
        return new ProductAddForm(
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
