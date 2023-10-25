package br.com.YagoPrazim.ApiPessoas.controllers;

import br.com.YagoPrazim.ApiPessoas.dtos.PessoaDto;
import br.com.YagoPrazim.ApiPessoas.models.Pessoa;
import br.com.YagoPrazim.ApiPessoas.repositories.PessoaRepository;
import br.com.YagoPrazim.ApiPessoas.services.PessoaService;
import br.com.YagoPrazim.ApiPessoas.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
    private PessoaService pessoaService;
    @Autowired
    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody @Valid PessoaDto pessoaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrarPessoa(pessoaDto));
    }
    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarPessoas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPessoas(paginacao));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> listarPessoa(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaService.listarPessoa(id);

        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse("Pessoa não encontrada."));
        }
    }
//    public ResponseEntity<Object> listarPessoa(@PathVariable Long id) {
//        Object response = pessoaService.listarPessoa(id);
//        if (response instanceof CustomResponse) {
//            return ResponseEntity.status(((CustomResponse) response).getStatusCode()).body(response);
//        }
//        return ResponseEntity.ok(response);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody  @Valid PessoaDto pessoaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.atualizarPessoa(id, pessoaDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deletarPessoa(@PathVariable Long id){
        CustomResponse response = pessoaService.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }












}
