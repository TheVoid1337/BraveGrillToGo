package entity;


import exceptions.entity.InvalidProductException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import valueObjects.Price;

@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer productID;

    @Getter
    @Embedded
    private String description;

    @Embedded
    private Price price;

    public Product(int productID, float price, String description){
        if (productID<=0)
            throw new InvalidProductException("Invalid product ID");
        if (description == null)
            throw new InvalidProductException("Description for product can not be null!");
        if (description.isBlank() || description.length() < 3)
            throw new InvalidProductException("Product description must contain at least 3 letters");

        this.price = new Price(price);
        this.productID = productID;
        this.description = description;
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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Product.class)){
            return productID.equals(((Product) obj).getProductID());
        }else {
            return false;
        }
    }
}
