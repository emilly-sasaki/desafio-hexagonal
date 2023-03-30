package br.com.itau.desafio.dominio;

import java.math.BigDecimal;
import javax.persistence.Entity;

// Classe que representa uma conta
@Entity
public class Conta {
    // Camada de Domínio
    
    private Long id;
    private String nome;
    private String cpf;
    private BigDecimal saldo;

    public Conta() {
        this.saldo = BigDecimal.ZERO;
    }

    public Conta(Long id, String nome, String cpf, BigDecimal saldo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
