package br.com.itau.desafio.dominio.adaptadores.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.itau.desafio.dominio.SaldoInsuficienteException;
import br.com.itau.desafio.infraestrutura.entidades.ContaEntity;
import br.com.itau.desafio.infraestrutura.repositorios.SpringContaRepository;

@Service
public class ContaServiceImpl {

    private SpringContaRepository contaRepository;

    public ContaServiceImpl(SpringContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaEntity criarConta(ContaEntity conta) {
        conta.setId(this.gerarIdConta());
        return contaRepository.save(conta);
    }

    public ContaEntity depositar(Long id, BigDecimal valor) {
        ContaEntity conta = this.buscarContaPorId(id);
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }

    public ContaEntity sacar(Long id, BigDecimal valor) throws SaldoInsuficienteException {
        ContaEntity conta = this.buscarContaPorId(id);
        BigDecimal novoSaldo = conta.getSaldo().subtract(valor);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Dando erro");
        }
        conta.setSaldo(novoSaldo);
        return contaRepository.save(conta);
    }

    private ContaEntity buscarContaPorId(Long id) {
        Optional<ContaEntity> conta = contaRepository.findById(id);
        if (conta.isEmpty()) {
            throw new SaldoInsuficienteException("n");
        }
        return conta.get();
    }

    private Long gerarIdConta() {
        return null;
        // Lógica para gerar o id da conta
    }
}

