package dev.java10x.desafio_itau_backend.estatistica;

import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import dev.java10x.desafio_itau_backend.transacao.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private EstatisticaService estatisticaService;

    @Test
    void deveRetornarEstatisticasZeradasQuandoListaVazia() {
        EstatisticaDTO resultado = estatisticaService.estatistica();

        assertThat(resultado.getCount()).isZero();
        assertThat(resultado.getSum()).isZero();
        assertThat(resultado.getAvg()).isZero();
        assertThat(resultado.getMin()).isZero();
        assertThat(resultado.getMax()).isZero();
    }

    @Test
    void deveCalcularEstatisticasApenasDeTransacoesRecentes() throws Exception {
        // Transação de 30 segundos atrás
        TransacaoDTO recente = new TransacaoDTO(new BigDecimal("100.0"), OffsetDateTime.now().minusSeconds(30));
        // Transação de 90 segundos atrás
        TransacaoDTO antiga = new TransacaoDTO(new BigDecimal("50.0"), OffsetDateTime.now().minusSeconds(90));

        List<TransacaoDTO> listaMockada = new ArrayList<>();

        when(transacaoRepository.listarTransacoes()).thenReturn(listaMockada);
        listaMockada.add(recente);
        listaMockada.add(antiga);

        EstatisticaDTO resultado = estatisticaService.estatistica();

        // Verificações
        assertThat(resultado.getCount()).isEqualTo(1);
        assertThat(resultado.getSum()).isEqualTo(100.0);
        assertThat(resultado.getAvg()).isEqualTo(100.0);
        assertThat(resultado.getMin()).isEqualTo(100.0);
        assertThat(resultado.getMax()).isEqualTo(100.0);
    }
}
