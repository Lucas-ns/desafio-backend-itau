package dev.java10x.desafio_itau_backend.estatistica;

import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "estatistica.config")
@Validated
public class EstatisticaConfig {

    @Positive(message = "Segundos deve ser positivo.")
    private Long segundos;

    public Long getSegundos() {
        return segundos;
    }

    public void setSegundos(Long segundos) {
        this.segundos = segundos;
    }
}
