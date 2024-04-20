package qrcodeapi.Exceptions;

public class ServiceFailureException extends RuntimeException {
    public ServiceFailureException(String message) {
        super(message);
    }
}
