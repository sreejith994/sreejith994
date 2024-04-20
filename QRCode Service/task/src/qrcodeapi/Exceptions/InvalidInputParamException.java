package qrcodeapi.Exceptions;

public class InvalidInputParamException extends RuntimeException {
    public InvalidInputParamException(String message) {
        super(message);
    }
}