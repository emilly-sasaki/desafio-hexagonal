package br.com.itau.desafio.aplicacao.adaptadores.controllers;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.desafio.dominio.Conta;
import br.com.itau.desafio.dominio.SaldoInsuficienteException;
import br.com.itau.desafio.dominio.adaptadores.services.ContaServiceImp;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaServiceImp contaServiceImp;

    public ContaController(ContaServiceImp contaService) {
        this.contaServiceImp = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        Conta novaConta = contaServiceImp.criarConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        BigDecimal valor = new BigDecimal(body.get("valor"));
        Conta contaAtualizada = contaServiceImp.depositar(id, valor);
        return ResponseEntity.ok(contaAtualizada);
    }

    @PutMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        BigDecimal valor = new BigDecimal(body.get("valor"));
        try {
            Conta contaAtualizada = contaServiceImp.sacar(id, valor);
            return ResponseEntity.ok(contaAtualizada);
        } catch (SaldoInsuficienteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

