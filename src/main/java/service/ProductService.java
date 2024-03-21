package service;

import entity.Product;
import exceptions.service.ProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.ProductPersistence;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductPersistence productPersistence;

    @Autowired
    public ProductService(ProductPersistence productPersistence){
        this.productPersistence = productPersistence;
    }

    public Integer addProduct(float price, String description){
        Product product = new Product(price,description);
        return this.productPersistence.save(product).getProductID();
    }

    public Product getProductByID(int id){
        Optional<Product> productOptional = productPersistence.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get();
        }else {
            throw new ProductServiceException("No Product found!");
        }
    }

    public void changeProductDescription(int id, String description){
        Optional<Product> productOptional = productPersistence.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            product.setDescription(description);
            this.productPersistence.save(product);
        }else {
            throw new ProductServiceException("No Product found!");
        }
    }

    public void changeProductPrice(int id, float price) {
        Optional<Product> productOptional = productPersistence.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setPrice(price);
            this.productPersistence.save(product);
        } else {
            throw new ProductServiceException("No Product found!");
        }
    }


}
