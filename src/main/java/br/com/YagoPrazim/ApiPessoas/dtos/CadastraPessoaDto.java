package br.com.YagoPrazim.ApiPessoas.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastraPessoaDto(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotNull
        Integer idade
) {

}
