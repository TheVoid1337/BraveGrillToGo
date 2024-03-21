package exceptions.valueObjects;

public class InvalidCustomerAddressException extends RuntimeException {
    public  InvalidCustomerAddressException(String message){
        super(message);
    }
}
