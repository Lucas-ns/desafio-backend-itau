package dev.java10x.desafio_itau_backend.estatistica;

import dev.java10x.desafio_itau_backend.transacao.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

@Service
public class EstatisticaService {

    private final TransacaoRepository repository;

    private final EstatisticaConfig estatisticaConfig;

    public EstatisticaService(TransacaoRepository repository, EstatisticaConfig estatisticaConfig) {
        this.repository = repository;
        this.estatisticaConfig = estatisticaConfig;
    }

    public EstatisticaDTO estatistica() {
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(estatisticaConfig.getSegundos());

        DoubleSummaryStatistics stats = repository.listarTransacoes().stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();

        return new EstatisticaDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin() == Double.POSITIVE_INFINITY ? 0 : stats.getMin(),
                stats.getMax() == Double.NEGATIVE_INFINITY ? 0 : stats.getMax());
    }
}
