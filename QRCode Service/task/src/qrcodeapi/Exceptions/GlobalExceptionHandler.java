package qrcodeapi.Exceptions;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import qrcodeapi.dto.AddToError;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidInputParamException.class)
    public ResponseEntity<AddToError> handleInvalidInputParamException(InvalidInputParamException ex) {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new AddToError(ex.getMessage()));
    }

}
