package valueObjects;

import exceptions.InvalidPostalCodeException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import types.CountryType;

import static org.junit.jupiter.api.Assertions.*;

 @SpringBootTest(classes = PostalCode.class)
class PostalCodeTest {

     @Test
     void createPostalCodeIncorrectTest(){
         assertThrows(InvalidPostalCodeException.class, ()->new PostalCode(null, CountryType.AU));
         assertThrows(InvalidPostalCodeException.class, ()->new PostalCode("", CountryType.CH));
         assertThrows(InvalidPostalCodeException.class, ()->new PostalCode("null", CountryType.CH));
         assertThrows(InvalidPostalCodeException.class, ()->new PostalCode("44322", CountryType.CH));
         assertThrows(InvalidPostalCodeException.class, ()->new PostalCode("4432", CountryType.DE));
     }

     @Test
     void createPostalCodeCorrectTest(){
         PostalCode postalCode = new PostalCode("3332",CountryType.CH);
         assertEquals(postalCode.getPostalCode(),"3332");
         assertEquals(postalCode.getCountry(),CountryType.CH);
     }

}