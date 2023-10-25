package br.com.YagoPrazim.ApiPessoas.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CustomResponse {
    private String mensagem;
    private List<String> errors;
    private int statusCode;

    public CustomResponse(String mensagem, int statusCode) {
        this.mensagem = mensagem;
        this.statusCode = statusCode;
    }
    public CustomResponse(String mensagem, int statusCode, List<String> errors) {
        this.mensagem = mensagem;
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public CustomResponse(String mensagem) {

        this.mensagem = mensagem;
    }
    public String getMensagem() {

        return mensagem;
    }
    @JsonIgnore
    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getErrors() {
        return errors;
    }
}
