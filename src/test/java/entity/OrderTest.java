package entity;

import exceptions.entity.InvalidOrderException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import types.CountryType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Order.class)
class OrderTest {

    @Test
    void createOrderIncorrectTest(){
        ArrayList<Product> products = new ArrayList<>(List.of(new Product(33.3f,"Steak")));
        Customer customer = new Customer();
        assertThrows(InvalidOrderException.class,()->new Order(null,products));
        assertThrows(InvalidOrderException.class, ()-> new Order(customer,null));
    }


    @Test
    void createOrderCorrectTest(){
        Customer customer = new Customer("Henry","Ford","Hasselstrasse",
                44,"44322", CountryType.DE,
                "test.test@example.de");
        ArrayList<Product> products = new ArrayList<>(List.of(new Product(33.3f,"Steak")));

        Order order = new Order(customer,products);

        assertEquals(customer,order.getCustomer());
        assertEquals(products,order.getProducts());

    }

}