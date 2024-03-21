package controller;


import entity.Customer;
import entity.Order;
import entity.Product;
import exceptions.entity.InvalidCustomerException;
import exceptions.entity.InvalidProductException;
import exceptions.service.OrderServiceException;
import exceptions.service.ProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import productresponse.OrderResponse;
import productresponse.ProductResponse;
import requestbody.CustomerRequestBody;
import requestbody.ProductRequestBody;
import service.CustomerService;
import service.OrderService;
import service.ProductService;
import types.CountryType;

import java.util.ArrayList;

@Controller
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    private final CustomerService customerService;

    @Autowired
    public OrderController(ProductService productService, OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }


    @PutMapping("/orders")
    public ResponseEntity<?> addOrder(@RequestBody CustomerRequestBody customerRequestBody,
                                      @RequestBody ArrayList<ProductRequestBody> productRequestBody){
        Integer orderID;
        try{

            Customer customer = new Customer();

            customer.setFirstName(customerRequestBody.getFirstName());

            customer.setLastName(customerRequestBody.getLastName());

            customer.setCustomerAddress(customerRequestBody.getStreet(),customerRequestBody.getHouseNumber(),
                    customerRequestBody.getPostalCode(), CountryType.valueOf(customerRequestBody.getCountry()));
            customer.setEmailAddress(customerRequestBody.getEmailAddress());

            ArrayList<Product> products = new ArrayList<>();

            for (ProductRequestBody requestBody: productRequestBody){
                products.add(new Product(requestBody.getProductID(), requestBody.getPrice(),requestBody.getDescription()));
            }

            orderID = this.orderService.addOder(customer,products);


            this.customerService.addCustomer(
                    customer.getFirstName(),
                    customer.getLastName(),
                    customerRequestBody.getStreet(),
                    customerRequestBody.getHouseNumber(),
                    customerRequestBody.getPostalCode(),
                    CountryType.valueOf(customerRequestBody.getCountry()),
                    customerRequestBody.getEmailAddress()
            );

        }catch (InvalidCustomerException customerException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Invalid input for Customer " +customerException.getMessage());
        }catch (InvalidProductException productException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Invalid Product"+ productException.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Added Order with "+orderID);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable Integer id){
        try {
           Product product = productService.getProductByID(id);

            ProductResponse response = new ProductResponse(product.getProductID(),
                    product.getDescription(), product.getPrice());

            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (ProductServiceException productServiceException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No product with "+id+" found");
        }
    }


    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        ArrayList<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderByID(@PathVariable Integer id){
        try {

            Order order = orderService.getOrderByID(id);

            OrderResponse orderResponse = new OrderResponse(order.getOderID(),order.getCustomer(),order.getProducts());

            return ResponseEntity.status(HttpStatus.OK).body(orderResponse);

        }catch (OrderServiceException orderServiceException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No oder with "+id+ "found");
        }
    }


    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){
        ArrayList<Order> orders = orderService.getAllOrders();
        ArrayList<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order: orders){
            orderResponses.add(new OrderResponse(order.getOderID(),order.getCustomer(),order.getProducts()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }

}
