package be.bstorm.graphql.models;


import lombok.Data;

@Data
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private float price;
}
