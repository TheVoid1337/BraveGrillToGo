package service;

import entity.Customer;
import entity.Order;
import entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import persistence.CustomerPersistence;
import persistence.OrderPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderPersistence orderPersistence;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addOder() {
        ArrayList<Product> products = new ArrayList<>(List.of(new Product(33.3f,"Steak")));
        Customer customer = new Customer();

        when(orderPersistence.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setOderID(1);
            return order;
        });

        orderService.addOder(customer,products);

        verify(orderPersistence,times(1)).save(any(Order.class));


    }

    @Test
    void getAllProducts() {
        ArrayList<Product> products = new ArrayList<>(List.of(new Product(33.3f,"Steak")));
        Customer customer = new Customer();


        when(orderPersistence.save(any(Order.class))).thenAnswer(invocation -> {
            Order order1 = invocation.getArgument(0);
            order1.setOderID(1);
            return order1;
        });

        Order order = new Order(customer,products);

        when(orderPersistence.findById(1)).thenReturn(Optional.of(order));

        Integer id = orderService.addOder(customer, products);

        ArrayList<Product> products1 = orderService.getAllProducts(id);
        assertEquals(products,products1);

    }

    @Test
    void getOrderPrice() {
        ArrayList<Product> products = new ArrayList<>(List.of(new Product(33.3f,"Steak"),
                new Product(6.44f,"Hamburger")));

        Customer customer = new Customer();

        when(orderPersistence.save(any(Order.class))).thenAnswer(invocation -> {
            Order order1 = invocation.getArgument(0);
            order1.setOderID(1);
            return order1;
        });

        Order order = new Order(customer,products);

        Integer id = orderService.addOder(customer,products);

        when(orderPersistence.findById(1)).thenReturn(Optional.of(order));

        Float price = orderService.getOrderPrice(id);
        assertEquals(order.getOrderPrice(),price);

    }
}