package service;

import entity.Customer;
import exceptions.service.CustomerServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import persistence.CustomerPersistence;
import types.CountryType;
import valueObjects.EmailAddress;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class CustomerServiceTest {

    @Mock
    private CustomerPersistence customerPersistence;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomer() {

        customerService.addCustomer("Hans", "Müller",
                "Regensburger Straße", 34,
                "33212", CountryType.DE,
                "test.test@example.com");

        verify(customerPersistence, times(1)).save(any(Customer.class));

    }

    @Test
    void getCustomerByEmail() {

        CustomerPersistence customerPersistence = mock(CustomerPersistence.class);

        when(customerPersistence.findCustomerByEmailAddress(any(EmailAddress.class)))
                .thenReturn(Optional.empty());

        CustomerService customerService = new CustomerService(customerPersistence);

        assertThrows(CustomerServiceException.class, () -> {
            customerService.getCustomerByEmail("test.test123@example.com");
        });

        Customer customer = new Customer("Hans", "Müller",
                "Regensburger Straße", 34,
                "33212", CountryType.DE,
                "test.test@example.com");

        when(customerPersistence.findCustomerByEmailAddress(any(EmailAddress.class)))
                .thenReturn(Optional.of(customer));


        Customer returnedCustomer = customerService.getCustomerByEmail("test.test@example.com");

        verify(customerPersistence, times(1)).findCustomerByEmailAddress(new EmailAddress("test.test@example.com"));

        assertEquals(customer, returnedCustomer);

    }
}