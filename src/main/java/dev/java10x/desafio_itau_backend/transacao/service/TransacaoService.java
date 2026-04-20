package dev.java10x.desafio_itau_backend.transacao.service;


import dev.java10x.desafio_itau_backend.exceptions.BusinessException;
import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import dev.java10x.desafio_itau_backend.transacao.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void criarTransacao(TransacaoDTO transacaoDTO) {
        if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            log.error("ERRO: Data não pode ser no futuro.");
            throw new BusinessException("Data deve ser menor que data atual.");
        }

        if(transacaoDTO.getValor().compareTo(BigDecimal.ZERO) < 0) {
            log.error("ERRO: Valor deve ser maior do que zero.");
            throw new BusinessException("Valor deve ser maior do que zero.");
        }

        transacaoRepository.salvarTransacao(transacaoDTO);
        log.info("INFO: Transação com valor R${} foi cadastrada com sucesso!", transacaoDTO.getValor() + "");
    }

    public void deletarTransacoes() {
        log.info("INFO: Transações foram excluídas!");
        transacaoRepository.deletarTransacoes();
    }



}
