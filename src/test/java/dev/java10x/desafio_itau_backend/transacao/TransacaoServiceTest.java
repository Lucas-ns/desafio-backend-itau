package dev.java10x.desafio_itau_backend.transacao;

import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import dev.java10x.desafio_itau_backend.exceptions.BusinessException;
import dev.java10x.desafio_itau_backend.transacao.repository.TransacaoRepository;
import dev.java10x.desafio_itau_backend.transacao.service.TransacaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;


    @Test
    void deveAdicionarTransacaoComSucesso() throws Exception {
        TransacaoDTO transacao = new TransacaoDTO(new BigDecimal("100.50"), OffsetDateTime.now());

        transacaoService.criarTransacao(transacao);

        verify(transacaoRepository, times(1)).salvarTransacao(transacao);

    }

    @Test
    void deveLancarExcessaoCasoValorSejaMenorDoQueZero() throws Exception {
        TransacaoDTO transacao = new TransacaoDTO(new BigDecimal("-100.50"), OffsetDateTime.now());

        BusinessException businessException = Assertions.assertThrows(BusinessException.class, () -> {
            transacaoService.criarTransacao(transacao);
        });

        Assertions.assertEquals("Valor deve ser maior do que zero.", businessException.getMessage());
    }

    @Test
    void deveLimparTransacoes() {
        transacaoService.deletarTransacoes();
        verify(transacaoRepository, times(1)).deletarTransacoes();
    }




}
