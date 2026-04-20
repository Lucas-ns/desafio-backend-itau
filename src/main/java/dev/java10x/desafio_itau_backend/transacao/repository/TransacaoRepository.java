package dev.java10x.desafio_itau_backend.transacao.repository;


import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransacaoRepository {

    private final List<TransacaoDTO> transacoes;

    public TransacaoRepository(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }

    public List<TransacaoDTO> listarTransacoes() {
        return transacoes;
    }

    public void salvarTransacao(TransacaoDTO transacaoDTO) {
        transacoes.add(transacaoDTO);
    }

    public void deletarTransacoes() {
        transacoes.clear();
    }
}
