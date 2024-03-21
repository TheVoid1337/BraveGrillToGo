package valueObjects;

import exceptions.valueObjects.InvalidCustomerAddressException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import types.CountryType;

@Embeddable
@NoArgsConstructor
public class CustomerAddress {

    @Getter
    private String street;

    @Getter
    private Integer houseNumber;

    private PostalCode postalCode;

    public CustomerAddress(String street, Integer houseNumber, String postalCode, CountryType country) {

        if (street == null)
            throw new InvalidCustomerAddressException("Street of CustomerAddress can not be null!");
        if (street.isBlank() || street.length()<4)
            throw new InvalidCustomerAddressException("Street of CustomerAddress must contain at least four characters!");
        if (houseNumber == null)
            throw new InvalidCustomerAddressException("House number can not be null!");
        if (houseNumber < 1)
            throw new InvalidCustomerAddressException("House number is invalid!");

        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = new PostalCode(postalCode,country);
    }

    public String getPostalCode() {
        return postalCode.getPostalCode();
    }

    public CountryType getPostalCodeCountry(){
        return postalCode.getCountry();
    }
}
