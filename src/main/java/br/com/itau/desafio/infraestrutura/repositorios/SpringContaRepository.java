package br.com.itau.desafio.infraestrutura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.desafio.infraestrutura.entidades.ContaEntity;

@Repository
public interface SpringContaRepository extends JpaRepository<ContaEntity, Long>  {
    Optional<ContaEntity> findById(Long id);
}
