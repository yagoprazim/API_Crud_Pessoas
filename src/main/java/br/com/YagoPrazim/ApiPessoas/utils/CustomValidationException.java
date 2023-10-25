package br.com.YagoPrazim.ApiPessoas.utils;

import java.util.List;

public class CustomValidationException extends RuntimeException{
    private List<String> errors;
    public CustomValidationException(String message) {
        super(message);
    }
    public CustomValidationException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
