package requestbody;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import valueObjects.Price;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestBody {

    private Integer productID;

    private String description;

    private float price;
}
