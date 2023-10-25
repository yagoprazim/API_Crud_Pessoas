package br.com.YagoPrazim.ApiPessoas.services;

import br.com.YagoPrazim.ApiPessoas.dtos.PessoaDto;
import br.com.YagoPrazim.ApiPessoas.models.Pessoa;
import br.com.YagoPrazim.ApiPessoas.repositories.PessoaRepository;
import br.com.YagoPrazim.ApiPessoas.utils.CustomResponse;
import br.com.YagoPrazim.ApiPessoas.utils.CustomValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrarPessoa(PessoaDto pessoaDto){
        List<String> errors = new ArrayList<>();
        // Verifique se o CPF já existe na base de dados
        if (pessoaRepository.existsByCpf(pessoaDto.cpf())) {
            errors.add("Já existe um usuário com este CPF.");
        }
        if (!errors.isEmpty()) {
            throw new CustomValidationException(errors);
        }

        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoaRepository.save(pessoa);
    }
//    public Object cadastrarPessoa(PessoaDto pessoaDto){
//        Pessoa pessoa = new Pessoa();
//        if(pessoaDto != null) {
//            BeanUtils.copyProperties(pessoaDto, pessoa);
//            pessoaRepository.save(pessoa);
//            return new CustomResponse("Pessoa cadastradas com sucesso.", HttpStatus.OK.value());
//        }else{
//            return new CustomResponse("Não foi possível cadastrar a pessoa.", HttpStatus.NOT_FOUND.value());
//        }
//
//    }

    public Page<Pessoa> listarPessoas(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao);
    }

    public Optional<Pessoa> listarPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

//    public Object listarPessoa(Long id) {
//        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
//        if(pessoa.isPresent()){
//            return pessoa;
//        }else{
//            return new CustomResponse("Não foi possível encontrar a pessoa com o ID especificado.", HttpStatus.NOT_FOUND.value());
//        }
//    }

    public Pessoa atualizarPessoa(Long id, PessoaDto pessoaDto) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (pessoa.isPresent()) {
            Pessoa pessoaAtualizada = pessoa.get();
            BeanUtils.copyProperties(pessoaDto, pessoaAtualizada);
            return pessoaRepository.save(pessoaAtualizada);
        }
        return null;
    }
    public CustomResponse delete(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return new CustomResponse("Pessoa deletada com sucesso.", HttpStatus.OK.value());
        } else {
            return new CustomResponse("Não foi possível encontrar a pessoa com o ID especificado.", HttpStatus.NOT_FOUND.value());
        }

    }






}
