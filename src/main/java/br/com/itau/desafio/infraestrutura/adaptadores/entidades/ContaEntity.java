package br.com.itau.desafio.infraestrutura.adaptadores.entidades;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.itau.desafio.dominio.Conta;

// Classe que representa uma conta
@Entity
@Table
public class ContaEntity {
    // Camada de Domínio
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private BigDecimal saldo;

    public ContaEntity() {
        
    }

    public ContaEntity(Conta conta) {
        this.nome = conta.getNome();
        this.cpf = conta.getCpf();
        this.saldo = conta.getSaldo();
    }

    public void atualizar(Conta conta) {
        this.nome = conta.getNome();
        this.cpf = conta.getCpf();
        this.saldo = conta.getSaldo();
    }

    public Conta toConta() {
        return new Conta(this.id, this.nome, this.cpf, this.saldo);
    }

}
