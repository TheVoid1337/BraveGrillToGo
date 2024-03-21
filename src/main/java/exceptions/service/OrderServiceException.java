package exceptions.service;

public class OrderServiceException extends RuntimeException{
    public OrderServiceException(String message){
        super(message);
    }
}
