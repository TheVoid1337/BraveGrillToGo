package service;

import entity.Customer;
import exceptions.service.CustomerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.CustomerPersistence;
import types.CountryType;
import valueObjects.EmailAddress;

import java.util.Optional;


@Service
public class CustomerService {
    private final CustomerPersistence customerPersistence;
    @Autowired
    public CustomerService(CustomerPersistence customerPersistence){
        this.customerPersistence = customerPersistence;
    }

    public void addCustomer(String firstName,
                            String lastName,
                            String street,
                            Integer houseNumber,
                            String postalCode,
                            CountryType country,
                            String email){

        Customer customer = new Customer(firstName,lastName,street,houseNumber,postalCode,country,email);
        this.customerPersistence.save(customer);
    }

    public Customer getCustomerByEmail(String email){
        EmailAddress emailAddress = new EmailAddress(email);
        Optional<Customer> customerOptional = customerPersistence.findCustomerByEmailAddress(emailAddress);
        if (customerOptional.isPresent()){
            return customerOptional.get();
        }
        throw new CustomerServiceException("No Customer found!");
    }

}
