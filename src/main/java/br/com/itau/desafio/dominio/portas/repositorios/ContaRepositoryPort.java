package br.com.itau.desafio.dominio.portas.repositorios;

import java.util.Optional;

import br.com.itau.desafio.infraestrutura.adaptadores.entidades.ContaEntity;

public interface ContaRepositoryPort {
    public ContaEntity salvar(ContaEntity conta);

    public Optional<ContaEntity> buscarPeloID(Long id);
}
