package dev.java10x.desafio_itau_backend.transacao;

import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface SwaggerDocs {
    @Operation(description = "Endpoint para criar uma nova transação")
    @ApiResponses(
            {
                    @ApiResponse(description = "Criado com sucesso", responseCode = "201",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoDTO.class)) }),

                    @ApiResponse(description = "Requisição inválida", responseCode = "422")
            }
    )
    ResponseEntity<Void> criarTransacao(TransacaoDTO transacaoDTO);

}
