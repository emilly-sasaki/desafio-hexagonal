package br.com.itau.desafio.infraestrutura.adaptadores.repositorios;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.itau.desafio.dominio.Conta;
import br.com.itau.desafio.dominio.portas.repositorios.ContaRepositoryPort;
import br.com.itau.desafio.infraestrutura.adaptadores.entidades.ContaEntity;

@Repository
public class ContaRepository implements ContaRepositoryPort{
    
    private final SpringContaRepository springContaRepository;

    public ContaRepository(SpringContaRepository springContaRepository) {
        this.springContaRepository = springContaRepository;
    }

    @Override
    public Conta save(Conta conta) {
        ContaEntity contaEntity;
        if (Objects.isNull(conta.getId())) {
            contaEntity = new ContaEntity(conta);
        }else{
            contaEntity = this.springContaRepository.findById(conta.getId()).get();
            contaEntity.atualizar(conta);
        }
        this.springContaRepository.save(contaEntity);
        return contaEntity.toConta();
    }

    @Override
    public Conta findById(Long id) {
        Optional<ContaEntity> contaEntity = this.springContaRepository.findById(id);
        if(contaEntity.isPresent()){
            return contaEntity.get().toConta();
        }
        throw new RuntimeException("Conta não existe");
        
    }
}
