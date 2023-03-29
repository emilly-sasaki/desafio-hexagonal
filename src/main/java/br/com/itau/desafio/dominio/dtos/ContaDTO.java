package br.com.itau.desafio.dominio.dtos;

public class ContaDTO {
    
    private Long id;
    private String nomeCorrentista;
    private String cpf;
    private Double saldo;

    public ContaDTO() {}

    public ContaDTO(Long id, String nomeCorrentista, String cpf, Double saldo) {
        this.id = id;
        this.nomeCorrentista = nomeCorrentista;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
