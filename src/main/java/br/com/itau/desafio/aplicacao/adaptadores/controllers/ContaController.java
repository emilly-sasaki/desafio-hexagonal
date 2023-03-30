package br.com.itau.desafio.aplicacao.adaptadores.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.desafio.dominio.dtos.ContaDTO;
import br.com.itau.desafio.infraestrutura.adaptadores.entidades.ContaEntity;
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
    public ResponseEntity<ContaDTO> criarConta(@RequestBody Conta conta) {
        ContaDTO novaConta = contaServiceImp.criarConta(conta);
        ContaDTO contaDTO = contaServiceImp.criarConta(novaConta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<ContaEntity> depositar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        BigDecimal valor = new BigDecimal(body.get("valor"));
        Conta contaAtualizada = contaServiceImp.depositar(id, valor);
        return ResponseEntity.ok(contaAtualizada);
    }

    @PutMapping("/{id}/sacar")
    public ResponseEntity<?> sacar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        BigDecimal valor = new BigDecimal(body.get("valor"));
        try {
            Conta contaAtualizada = contaServiceImp.sacar(id, valor);
            return ResponseEntity.ok(contaAtualizada);
        } catch (SaldoInsuficienteException e) {
            return ResponseEntity.badRequest().body("Saque não permitido - Saldo insuficiente");
        }
    }

}

