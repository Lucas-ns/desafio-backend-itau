package dev.java10x.desafio_itau_backend.transacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransacaoDTO {

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura")
    private OffsetDateTime dataHora;

}
