package dev.java10x.desafio_itau_backend.estatistica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstatisticaDTO {
    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

}
