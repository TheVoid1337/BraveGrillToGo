package entity;


import exceptions.InvalidProductException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import valueObjects.Price;

@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer productID;

    @Getter
    @Embedded
    private String description;

    @Embedded
    private Price price;

    public Product(int productID, float price){
        if (productID<=0)
            throw new InvalidProductException("Invalid product ID");
        this.price = new Price(price);
    }

    public Product(float price, String description){
        if (description == null)
            throw new InvalidProductException("Description for product can not be null!");
        if (description.isBlank() || description.length() < 3)
            throw new InvalidProductException("Product description must contain at least 3 letters");

        this.price = new Price(price);
        this.description = description;
    }

    public float getPrice(){
        return price.getPrice();
    }

    public void setDescription(String description) {
        if (description == null)
            throw new InvalidProductException("Description for product can not be null!");
        if (description.isBlank() || description.length() < 3)
            throw new InvalidProductException("Product description must contain at least 3 letters");

        this.description = description;
    }

    public void setPrice(float price){
        this.price = new Price(price);
    }
}
