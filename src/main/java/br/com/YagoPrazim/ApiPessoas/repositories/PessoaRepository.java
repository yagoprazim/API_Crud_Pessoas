package br.com.YagoPrazim.ApiPessoas.repositories;

import br.com.YagoPrazim.ApiPessoas.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
