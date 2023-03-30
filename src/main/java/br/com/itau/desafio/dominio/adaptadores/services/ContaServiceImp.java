package br.com.itau.desafio.dominio.adaptadores.services;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.itau.desafio.dominio.Conta;
import br.com.itau.desafio.dominio.SaldoInsuficienteException;
import br.com.itau.desafio.dominio.portas.interfaces.ContaServicePort;
import br.com.itau.desafio.dominio.portas.repositorios.ContaRepositoryPort;

@Service
public class ContaServiceImp implements ContaServicePort{

    private final ContaRepositoryPort contaRepository;

    public ContaServiceImp(ContaRepositoryPort contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criarConta(Conta conta) {
        conta.setId(this.gerarIdConta());
        Conta contaCriada = contaRepository.save(conta);
        return contaCriada;
    }

    public Conta depositar(Long id, BigDecimal valor) {
        Conta conta = this.buscarContaPorId(id);
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }

    public Conta sacar(Long id, BigDecimal valor) throws SaldoInsuficienteException {
        Conta conta = this.buscarContaPorId(id);
        BigDecimal novoSaldo = conta.getSaldo().subtract(valor);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Dando erro");
        }
        conta.setSaldo(novoSaldo);
        return contaRepository.save(conta);
    }

    private Conta buscarContaPorId(Long id) {
        Conta conta = contaRepository.findById(id);
        if (Objects.isNull(conta)) {
            throw new SaldoInsuficienteException("n");
        }
        return conta;
    }

    private Long gerarIdConta() {
        return null;
        // Lógica para gerar o id da conta
    }
}

