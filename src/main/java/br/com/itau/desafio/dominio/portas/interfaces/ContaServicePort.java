package br.com.itau.desafio.dominio.portas.interfaces;

import java.math.BigDecimal;

import br.com.itau.desafio.dominio.Conta;
import br.com.itau.desafio.dominio.dtos.ContaDTO;

public interface ContaServicePort {
    
    public Conta criarConta(Conta conta);

    public Conta depositar(Long id, BigDecimal valor);

    public Conta sacar(Long id, BigDecimal valor);

    public ContaDTO toContaDTO(Conta conta);
}
