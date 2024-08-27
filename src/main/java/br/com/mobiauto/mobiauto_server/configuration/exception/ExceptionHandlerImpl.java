package br.com.mobiauto.mobiauto_server.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(NoneResultException.class)
    public ResponseEntity<ExceptionDto> handleNoneResultException(NoneResultException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionEntity);
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ExceptionDto> handleOperationException(OperationException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionEntity);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionEntity);
    }

}
