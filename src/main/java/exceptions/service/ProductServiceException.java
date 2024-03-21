package exceptions.service;

public class ProductServiceException extends RuntimeException {
    public ProductServiceException(String message){
        super(message);
    }
}
