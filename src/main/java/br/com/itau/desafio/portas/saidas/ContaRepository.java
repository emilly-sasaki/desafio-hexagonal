package br.com.itau.desafio.portas.saidas;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itau.desafio.dominio.entidades.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>  {

}
