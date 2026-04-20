package dev.java10x.desafio_itau_backend.transacao.controller;


import dev.java10x.desafio_itau_backend.estatistica.EstatisticaDTO;
import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import dev.java10x.desafio_itau_backend.transacao.service.TransacaoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criarTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        service.criarTransacao(transacaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        service.deletarTransacoes();
        return ResponseEntity.ok().build();
    }

}
