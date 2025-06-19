package be.bstorm.java.freemarker.dal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private long price;
    private boolean active = true;

    public double getPrice() {
        return price / 100.0;
    }

    public void setPrice(double price) {
        this.price = (long) (price * 100);
    }
}
