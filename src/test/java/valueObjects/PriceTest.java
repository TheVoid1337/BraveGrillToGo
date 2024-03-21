package valueObjects;

import exceptions.valueObjects.InvalidPriceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Price.class)
class PriceTest {

    @Test
    void createPriceIncorrectTest(){
        assertThrows(InvalidPriceException.class, ()->new Price(-10.0f));
        assertThrows(InvalidPriceException.class, ()->new Price(-0.0f));
    }

    @Test
    void createPriceCorrectTest(){
        Price price = new Price(3.44f);
        assertEquals(3.44f, price.getPrice());
    }

}