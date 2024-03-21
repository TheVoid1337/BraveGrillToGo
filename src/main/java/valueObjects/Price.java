package valueObjects;

import exceptions.valueObjects.InvalidPriceException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Price {

    private float price;


    public Price(float price) {
        if (price<=0)
            throw new InvalidPriceException("Price of product cannot be negative or zero!");
        this.price = Float.valueOf(price);
    }


}
