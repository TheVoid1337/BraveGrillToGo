package service;


import entity.Customer;
import entity.Order;
import entity.Product;
import exceptions.service.OrderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.OrderPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    private final OrderPersistence persistence;

    @Autowired
    public OrderService(OrderPersistence persistence){
        this.persistence = persistence;
    }

    public Integer addOder(Customer customer, ArrayList<Product> products ){
        Order order = new Order(customer,products);
        return this.persistence.save(order).getOderID();
    }

    public ArrayList<Product> getAllProducts(Integer orderID){
        Optional<Order> orderOptional  = persistence.findById(orderID);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            return order.getProducts();
        }else {
            throw new OrderServiceException("No order found!");
        }
    }

    public Float getOrderPrice(Integer orderID){
        Optional<Order> orderOptional  = persistence.findById(orderID);
        if (orderOptional.isPresent()){
            return orderOptional.get().getOrderPrice();
        }else {
            throw new OrderServiceException("No order found!");
        }
    }

    public Order getOrderByID(Integer orderID){
        Optional<Order> orderOptional  = persistence.findById(orderID);
        if (orderOptional.isPresent()){
            return orderOptional.get();
        }else {
            throw new OrderServiceException("No order found!");
        }
    }

    public ArrayList<Order> getAllOrders(){
        List<Order> orders =  StreamSupport.stream(persistence.findAll().spliterator(), false)
                .toList();
        return new ArrayList<>(orders);
    }


}
