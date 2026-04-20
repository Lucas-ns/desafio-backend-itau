package dev.java10x.desafio_itau_backend.transacao;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.java10x.desafio_itau_backend.transacao.controller.TransacaoController;
import dev.java10x.desafio_itau_backend.transacao.dto.TransacaoDTO;
import dev.java10x.desafio_itau_backend.transacao.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransacaoService transacaoService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // Registra suporte a OffsetDateTime
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // Envia como String ISO, não números
            .disable(MapperFeature.ALLOW_COERCION_OF_SCALARS); // Desativar conversão de double para String

    @Test
    void deveRetornar201AoCriarTransacaoValida() throws Exception {
        TransacaoDTO dto = new TransacaoDTO(new BigDecimal("100.0"), OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornar400QuandoNaoHouverCorpo() throws Exception {
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar422QuandoDataForNoFuturo() throws Exception {
        // Transação Futura
        TransacaoDTO dtoFuturo = new TransacaoDTO(new BigDecimal("10.0"), OffsetDateTime.now().plusDays(1));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoFuturo)))
                .andExpect(status().isUnprocessableContent());
    }

    @Test
    void deveRetornar422QuandoValorForNegativo() throws Exception {
        TransacaoDTO dtoNegativo = new TransacaoDTO(new BigDecimal("-5.0"), OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoNegativo)))
                .andExpect(status().isUnprocessableContent());
    }


    @Test
    void deveRetornar200AoDeletarTransacoes() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}