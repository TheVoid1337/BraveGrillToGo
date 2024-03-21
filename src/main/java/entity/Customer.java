package entity;


import exceptions.entity.InvalidCustomerException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import types.CountryType;
import valueObjects.CustomerAddress;
import valueObjects.EmailAddress;

@Entity
@NoArgsConstructor
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer customerID;

    @Getter
    @Embedded
    private String firstName;

    @Getter
    @Embedded
    private String lastName;

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    @Getter
    private CustomerAddress customerAddress;


    public Customer(String firstName,
                    String lastName,
                    String street,
                    Integer houseNumber,
                    String postalCode,
                    CountryType country,
                    String email) {
        if (firstName == null)
            throw new InvalidCustomerException("Fist name of customer can not be null!");
        if (firstName.isBlank() || firstName.length()<2 )
            throw new InvalidCustomerException("Fist name  of customer must contain at least 2 characters!");
        if (lastName == null)
            throw new InvalidCustomerException("Last name of customer can not be null!");
        if (lastName.isBlank() || lastName.length()<2 )
            throw new InvalidCustomerException("Last name  of customer must contain at least 2 characters!");

        this.firstName = firstName;
        this.lastName = lastName;
        this.customerAddress = new CustomerAddress(street,houseNumber,postalCode,country);
        this.emailAddress = new EmailAddress(email);
    }


    public void setFirstName(String firstName) {
        if (firstName == null)
            throw new InvalidCustomerException("Fist name of customer can not be null!");
        if (firstName.isBlank() || firstName.length()<2 )
            throw new InvalidCustomerException("Fist name  of customer must contain at least 2 characters!");
        this.firstName = firstName;
    }

    public void setLastName (String lastName) {
        if (lastName == null)
            throw new InvalidCustomerException("Last name of customer can not be null!");
        if (lastName.isBlank() || lastName.length()<2)
            throw new InvalidCustomerException("Last name  of customer must contain at least 2 characters!");
        this.lastName = firstName;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = new EmailAddress(emailAddress);
    }

    public String getEmailAddress() {
        return emailAddress.getEmailAddress();
    }

    public void setCustomerAddress(String street, Integer houseNumber, String postalCode,  CountryType countryType) {
        this.customerAddress = new CustomerAddress(street,houseNumber,postalCode,countryType);
    }
}
