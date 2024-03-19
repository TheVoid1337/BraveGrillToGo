package valueObjects;

import exceptions.InvalidCustomerAddressException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import types.CountryType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = CustomerAddress.class)
class CustomerAddressTest {

    @Test
    void  createAddressIncorrectTest(){
        assertThrows(InvalidCustomerAddressException.class,()->new CustomerAddress(
                null,12,"42363", CountryType.DE));
        assertThrows(InvalidCustomerAddressException.class,()->new CustomerAddress(
                "",12,"42363", CountryType.DE));
        assertThrows(InvalidCustomerAddressException.class,()->new CustomerAddress(
                "tes",12,"42363", CountryType.DE));
        assertThrows(InvalidCustomerAddressException.class,()->new CustomerAddress(
                "Herbst-Allee",null,"42363", CountryType.DE));
        assertThrows(InvalidCustomerAddressException.class,()->new CustomerAddress(
                "Herbst-Allee",-1,"42363", CountryType.DE));
        assertThrows(InvalidCustomerAddressException.class,()->new CustomerAddress(
                "Herbst-Allee",0,"42363", CountryType.DE));
    }


    @Test
    void createAddressCorrectTest(){
        CustomerAddress customerAddress = new CustomerAddress("Herbst-Allee",
                23,"4422",CountryType.AU);

        assertEquals("Herbst-Allee",customerAddress.getStreet());
        assertEquals(customerAddress.getHouseNumber(),23);
        assertEquals(customerAddress.getPostalCode(),"4422");
        assertEquals(customerAddress.getPostalCodeCountry(),CountryType.AU);
    }

}