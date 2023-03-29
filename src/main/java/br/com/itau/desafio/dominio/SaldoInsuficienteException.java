package br.com.itau.desafio.dominio;

public class SaldoInsuficienteException extends RuntimeException {
    
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}