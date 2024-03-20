package service;

import entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import persistence.ProductPersistence;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductService.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductPersistence productPersistenceMock;

    @Test
    void addProduct() {

        when(productPersistenceMock.save(any())).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            product.setProductID(1);
            return product;
        });

        int productId = productService.addProduct(3.33f, "Cola");

        assertEquals(1, productId);

    }

    @Test
    void getProductByID() {
        Product product = new Product(1, 22.34f, "Menü 1");

        when(productPersistenceMock.save(any())).thenAnswer(invocation -> {
            Product product1 = invocation.getArgument(0);
            product1.setProductID(1);
            return product1;
        });

        when(productPersistenceMock.findById(any())).thenReturn(Optional.of(product));

        Integer prodID  = this.productService.addProduct(product.getPrice(),product.getDescription());

         Product product1 = this.productService.getProductByID(prodID);

        assertEquals(product.getProductID(),product1.getProductID());
        assertEquals(product.getPrice(),product1.getPrice());
        assertEquals(product.getDescription(),product1.getDescription());

    }

    @Test
    void changeProductDescription() {

        Product newProduct = new Product(1, 22.34f, "Menü 1");
        when(productPersistenceMock.findById(any())).thenReturn(Optional.of(newProduct));
        //newProduct.setDescription("Menü 2");
        when(productPersistenceMock.save(any())).thenAnswer(invocation -> {
            Product product1 = invocation.getArgument(0);
            product1.setDescription("Menü 2");
            return product1;
        });
        productService.changeProductDescription(newProduct.getProductID(),"Menü 2");
        Product updatedProduct = productService.getProductByID(1);

        assertEquals(1, updatedProduct.getProductID());
        assertEquals(newProduct.getPrice(), updatedProduct.getPrice());
        assertEquals("Menü 2", updatedProduct.getDescription());



    }

    @Test
    void changeProductPrice() {

        Product newProduct = new Product(1, 22.34f, "Menü 1");
        when(productPersistenceMock.findById(any())).thenReturn(Optional.of(newProduct));


        when(productPersistenceMock.save(any())).thenAnswer(invocation -> {
            Product product1 = invocation.getArgument(0);
            product1.setPrice(12.64f);
            return product1;
        });

        productService.changeProductPrice(newProduct.getProductID(),12.64f);
        Product updatedProduct = productService.getProductByID(1);

        assertEquals(1, updatedProduct.getProductID());
        assertEquals(newProduct.getPrice(), updatedProduct.getPrice());
        assertEquals("Menü 1", updatedProduct.getDescription());

    }
}