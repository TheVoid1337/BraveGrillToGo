package exceptions.valueObjects;

public class InvalidPostalCodeException extends RuntimeException{
    public InvalidPostalCodeException(String message){
        super(message);
    }
}
