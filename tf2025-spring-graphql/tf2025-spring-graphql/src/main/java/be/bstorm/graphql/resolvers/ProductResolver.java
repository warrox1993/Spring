package be.bstorm.graphql.resolvers;

import be.bstorm.graphql.models.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
public class ProductResolver {
    @QueryMapping
    public List<ProductEntity> findAllProduct() {
        log.info("FINDALL");
        return null;
    }

    @QueryMapping
    public ProductEntity findOneProduct(@Argument Long id) {
        log.info("FINDONE");
        return null;
    }
}
