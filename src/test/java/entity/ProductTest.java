package entity;

import exceptions.InvalidProductException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Product.class)
class ProductTest {

    @Test
    void createProductIncorrectTest(){
        assertThrows(InvalidProductException.class, ()->new Product(3.33f,null));
        assertThrows(InvalidProductException.class, ()->new Product(3.33f,""));
        assertThrows(InvalidProductException.class, ()->new Product(3.33f,"te"));
        }


    @Test
    void createProductCorrectTest(){
        Product product = new Product(12.54f,"Hamburger");
        assertEquals(12.54f, product.getPrice());
        assertEquals("Hamburger",product.getDescription());
    }

}