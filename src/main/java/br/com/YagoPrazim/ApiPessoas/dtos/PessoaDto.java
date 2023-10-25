package br.com.YagoPrazim.ApiPessoas.dtos;

import br.com.YagoPrazim.ApiPessoas.models.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PessoaDto(
        @NotBlank(message = "Preencha o campo Nome.")
        String nome,
        @NotBlank(message = "Preencha o campo Cpf.")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")
        String cpf,
        @NotBlank(message = "Preencha o campo Email.")
        @Email(message = "O formato do email é inválido")
        String email,
        @NotNull(message = "Preencha o campo Idade.")
        Integer idade
) {
}
