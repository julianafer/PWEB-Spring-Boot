package br.hall.healhub.api.model;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
