package dev.java10x.desafio_itau_backend.estatistica;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstatisticaController.class)
public class EstatisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EstatisticaService estatisticaService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // Registra suporte a OffsetDateTime
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // Envia como String ISO, não números
            .disable(MapperFeature.ALLOW_COERCION_OF_SCALARS); // Desativar conversão de double para String

    @Test
    void deveRetornarEstatisticasComSucesso() throws Exception {
        EstatisticaDTO mockEstatistica = new EstatisticaDTO(1L, 100.0, 100.0, 100.0, 100.0);

        // Quando o controller chamar o service, retorne nosso mock
        when(estatisticaService.estatistica()).thenReturn(mockEstatistica);

        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.sum").value(100.0))
                .andExpect(jsonPath("$.avg").value(100.0))
                .andExpect(jsonPath("$.min").value(100.0))
                .andExpect(jsonPath("$.max").value(100.0));
    }
}
