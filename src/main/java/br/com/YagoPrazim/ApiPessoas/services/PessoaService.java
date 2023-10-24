package br.com.YagoPrazim.ApiPessoas.services;

import br.com.YagoPrazim.ApiPessoas.dtos.CadastraPessoaDto;
import br.com.YagoPrazim.ApiPessoas.models.Pessoa;
import br.com.YagoPrazim.ApiPessoas.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }



}
