package br.com.itau.desafio.infraestrutura.adaptadores.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.desafio.infraestrutura.adaptadores.entidades.ContaEntity;

@Repository
public interface SpringContaRepository extends JpaRepository<ContaEntity, Long>  {
    Optional<ContaEntity> findById(Long id);
}
