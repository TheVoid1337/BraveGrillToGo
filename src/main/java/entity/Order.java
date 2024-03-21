package entity;


import exceptions.entity.InvalidOrderException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer oderID;

    @ManyToOne
    @Getter
    private Customer customer;

    @OneToMany
    @Getter
    private ArrayList<Product> products;

    public Order(Customer customer, ArrayList<Product> products){
        if (customer == null)
            throw new InvalidOrderException("Customer of order cannot be null!");
        if (products==null||products.isEmpty())
            throw  new InvalidOrderException("Product list of order cannot be null or empty!");
        this.customer = customer;
        this.products = products;
    }

    public Order(Integer oderID, Customer customer, ArrayList<Product> products){
        this.oderID = oderID;
        this.customer = customer;
        this.products = products;
    }

    public float getOrderPrice(){
        float fullPrice = 0.0f;
        for (Product product: products){
            fullPrice+=product.getPrice();
        }
        return fullPrice;
    }


}
