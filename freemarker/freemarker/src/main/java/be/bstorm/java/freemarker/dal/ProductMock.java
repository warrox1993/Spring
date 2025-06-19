package be.bstorm.java.freemarker.dal;

import be.bstorm.java.freemarker.dal.entities.ProductEntity;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMock {
    private static List<ProductEntity> entities = Lists.newArrayList(
            new ProductEntity(1L, "Product 1", "Desc product 1", 100L, true)
    );

    public List<ProductEntity> getProducts(boolean active) {
        return entities.stream()
                .filter(p -> p.isActive() == active)
                .toList();
    }

    public List<ProductEntity> getProducts() {
        return entities.stream()
                .filter(ProductEntity::isActive)
                .toList();
    }

    public ProductEntity getProduct(int id) {
        return entities.stream().filter(p -> p.getId() == id).findFirst().orElseThrow();
    }

    public ProductEntity addProduct(ProductEntity entity) {
        long lastId = entities.size() + 1;
        entity.setId(lastId + 1);
        entities.add(entity);

        return entity;
    }

    public void deleteProduct(int id) {
        ProductEntity entity = entities.stream().filter(p -> p.getId() == id).findFirst().orElseThrow();

        entity.setActive(false);
    }
}
