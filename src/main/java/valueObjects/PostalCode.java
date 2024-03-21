package valueObjects;

import exceptions.valueObjects.InvalidPostalCodeException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import types.CountryType;

@Embeddable
@NoArgsConstructor
@Getter
public class PostalCode {

    private String postalCode;

    private CountryType country;

    public PostalCode(String code, CountryType countryType){
        if (code == null)
            throw new InvalidPostalCodeException("Postal codes can not be null!");

        try {
           Integer.valueOf(code);
        }catch (NumberFormatException exception){
            throw new InvalidPostalCodeException(exception.getMessage());
        }

        if (code.isBlank()|| code.length()<4)
            throw new InvalidPostalCodeException("Postal codes must contain 4 to 5 digits!");

        if (countryType!=CountryType.DE && code.length()==5)
            throw new InvalidPostalCodeException("Postal codes with 5 digits are valid for germany only!");

        if (countryType == CountryType.DE && code.length()!=5)
            throw new InvalidPostalCodeException("Postal codes for Germany must be 5 digits long!");

        this.postalCode = code;
        this.country = countryType;
    }


}
