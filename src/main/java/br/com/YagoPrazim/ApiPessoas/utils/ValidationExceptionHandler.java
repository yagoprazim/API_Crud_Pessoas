package br.com.YagoPrazim.ApiPessoas.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        List<String> errorMessages = errors.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new CustomResponse("Erro de validação", HttpStatus.BAD_REQUEST.value(), errorMessages));
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleCustomValidationException(CustomValidationException customValidationException) {
        List<String> errorMessages = customValidationException.getErrors();

        return ResponseEntity.badRequest().body(new CustomResponse("Erro de validação", HttpStatus.BAD_REQUEST.value(), errorMessages));
    }
}

