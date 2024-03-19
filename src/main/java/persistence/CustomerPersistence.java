package persistence;

import entity.Customer;
import org.springframework.data.repository.CrudRepository;
import valueObjects.EmailAddress;

import java.util.Optional;

public interface CustomerPersistence extends CrudRepository<Customer,Integer> {
    public Optional<Customer> findCustomerByEmailAddress(EmailAddress emailAddress);
}
