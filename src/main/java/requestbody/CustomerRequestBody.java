package requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import types.CountryType;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class CustomerRequestBody {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String street;

    private Integer houseNumber;

    private String postalCode;

    private String country;
}
