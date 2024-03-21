package exceptions.entity;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message){
        super(message);
    }
}
