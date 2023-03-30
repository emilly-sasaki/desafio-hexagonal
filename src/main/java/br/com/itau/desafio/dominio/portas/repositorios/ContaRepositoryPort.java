package br.com.itau.desafio.dominio.portas.repositorios;

import java.util.Optional;

import br.com.itau.desafio.dominio.Conta;

public interface ContaRepositoryPort {
    public Conta save(Conta conta);

    public Conta findById(Long id);
}
