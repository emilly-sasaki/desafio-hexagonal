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
import br.com.itau.desafio.dominio.dtos.ContaDTO;
import br.com.itau.desafio.dominio.dtos.SaqueDTO;
import br.com.itau.desafio.dominio.portas.interfaces.ContaServicePort;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaServicePort contaServiceImp;

    public ContaController(ContaServiceImp contaService) {
        this.contaServiceImp = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> criarConta(@RequestBody Conta conta) {
        Conta novaConta = contaServiceImp.criarConta(conta);
        ContaDTO responseDTO = contaServiceImp.toContaDTO(novaConta);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<ContaDTO> depositar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        BigDecimal valor = new BigDecimal(body.get("valor"));
        Conta contaAtualizada = contaServiceImp.depositar(id, valor);
        ContaDTO responseDTO = contaServiceImp.toContaDTO(contaAtualizada);
	        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}/sacar")
    public ResponseEntity<?> sacar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        BigDecimal valor = new BigDecimal(body.get("valor"));
        try {
            Conta contaAtualizada = contaServiceImp.sacar(id, valor);
            SaqueDTO responseDTO = new SaqueDTO(contaAtualizada.getId(), contaAtualizada.getSaldo());
            return ResponseEntity.ok(responseDTO);
        } catch (SaldoInsuficienteException e) {
            return ResponseEntity.badRequest().body("Saque não permitido - Saldo insuficiente");
        }
    }

}

