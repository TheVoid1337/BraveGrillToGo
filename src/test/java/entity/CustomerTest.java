package entity;

import exceptions.InvalidCustomerException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import types.CountryType;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Customer.class)
class CustomerTest {
    @Test
    void createCustomerIncorrectTest(){
        assertThrows(InvalidCustomerException.class, ()->new Customer(null,"Müller",
                "Regensburger Straße",33,"33213", CountryType.DE,"test.test@example.com"));
        assertThrows(InvalidCustomerException.class, ()->new Customer("","Müller",
                "Regensburger Straße",33,"33213", CountryType.DE,"test.test@example.com"));
        assertThrows(InvalidCustomerException.class, ()->new Customer("l","Müller",
                "Regensburger Straße",33,"33213", CountryType.DE,"test.test@example.com"));
        assertThrows(InvalidCustomerException.class, ()->new Customer("Hans",null,
                "Regensburger Straße",33,"33213", CountryType.DE,"test.test@example.com"));
        assertThrows(InvalidCustomerException.class, ()->new Customer("Hans","",
                "Regensburger Straße",33,"33213", CountryType.DE,"test.test@example.com"));
        assertThrows(InvalidCustomerException.class, ()->new Customer("Hans","s",
                "Regensburger Straße",33,"33213", CountryType.DE,"test.test@example.com"));
    }

    @Test
    void createCustomerCorrectTest(){
        Customer customer = new Customer("Hans", "Müller",
                "Regensburger Straße", 34,"33212",CountryType.DE,"test.test@example.com");

        assertEquals("Hans",customer.getFirstName());
        assertEquals("Müller",customer.getLastName());
        assertEquals("Regensburger Straße",customer.getCustomerAddress().getStreet());
        assertEquals(34,customer.getCustomerAddress().getHouseNumber());
        assertEquals("33212",customer.getCustomerAddress().getPostalCode());
        assertEquals(CountryType.DE,customer.getCustomerAddress().getPostalCodeCountry());
        assertEquals("test.test@example.com",customer.getEmailAddress());

    }

}