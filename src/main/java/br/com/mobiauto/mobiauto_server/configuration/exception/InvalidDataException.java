package br.com.mobiauto.mobiauto_server.configuration.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message) {
        super(message);
    }
}
