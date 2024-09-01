package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.core.entity.*;
import br.com.mobiauto.mobiauto_server.dataprovider.OportunidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OportunidadeControllerTest {

    private final MockMvc mockMvc;
    private final OportunidadeRepository oportunidadeRepository;

    private Revenda revenda;
    private Usuario gerente;
    private Usuario assistente1;
    private Usuario assistente2;
    private Oportunidade oportunidade;

    @Autowired
    public OportunidadeControllerTest(MockMvc mockMvc, OportunidadeRepository oportunidadeRepository) {
        this.mockMvc = mockMvc;
        this.oportunidadeRepository = oportunidadeRepository;
    }

    @BeforeEach
    public void setup() {
        // Configuração inicial para criar uma revenda e usuários para os testes
        revenda = new Revenda();
        revenda.setId(10L);
        revenda.setCnpj("58184554000176");
        revenda.setNomeSocial("Revenda Teste");
        revenda.setAtivo(true);

        gerente = new Usuario();
        gerente.setId(24L);
        gerente.setNome("Gerente Teste");
        gerente.setEmail("gerente@teste.com");
        gerente.setSenha("123456");
        gerente.setCargo(Cargo.GERENTE);
        gerente.setRevenda(revenda);

        assistente1 = new Usuario();
        assistente1.setId(25L);
        assistente1.setNome("Assistente 1");
        assistente1.setEmail("assistente1@teste.com");
        assistente1.setSenha("123456");
        assistente1.setCargo(Cargo.ASSISTENTE);
        assistente1.setRevenda(revenda);

        assistente2 = new Usuario();
        assistente2.setId(26L);
        assistente2.setNome("Assistente 2");
        assistente2.setEmail("assistente2@teste.com");
        assistente2.setSenha("123456");
        assistente2.setCargo(Cargo.ASSISTENTE);
        assistente2.setRevenda(revenda);

        oportunidade = new Oportunidade();
        oportunidade.setId(3L);
        oportunidade.setStatus(Status.EM_ATENDIMENTO);
        oportunidade.setRevenda(revenda);
        oportunidade.setUsuario(assistente1);
        oportunidade.setDataAtribuicao(Date.valueOf(LocalDate.now()));
    }

    @Test
    @WithMockUser(username = "gerente@teste.com", roles = "GERENTE")
    public void testGerentePodeTransferirOportunidade() throws Exception {
        mockMvc.perform(put("/oportunidades/" + oportunidade.getId() + "/transferir/" + assistente2.getId()))
                .andExpect(status().isOk());

        Oportunidade oportunidadeTransferida = oportunidadeRepository.findById(oportunidade.getId()).orElse(null);
        assert oportunidadeTransferida != null;
        assert oportunidadeTransferida.getUsuario().getId().equals(assistente2.getId());
    }

    @Test
    @WithMockUser(username = "assistente1@teste.com", roles = "ASSISTENTE")
    public void testAssistenteNaoPodeTransferirOportunidade() throws Exception {
        mockMvc.perform(put("/oportunidades/" + oportunidade.getId() + "/transferir/" + assistente2.getId()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "assistente1@teste.com", roles = "ASSISTENTE")
    public void testAssistenteNaoPodeEditarOutraOportunidade() throws Exception {
        mockMvc.perform(put("/oportunidades/" + oportunidade.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"EM_ATENDIMENTO\", \"motivoConclusao\": null, \"ativo\": true}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "assistente2@teste.com", roles = "ASSISTENTE")
    public void testAssistentePodeEditarSuaOportunidade() throws Exception {
        mockMvc.perform(put("/oportunidades/" + oportunidade.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"EM_ATENDIMENTO\", \"motivoConclusao\": null, \"ativo\": false}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "gerente@teste.com", roles = "GERENTE")
    public void testGerentePodeEditarQualquerOportunidade() throws Exception {
        mockMvc.perform(put("/oportunidades/" + oportunidade.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"EM_ATENDIMENTO\", \"motivoConclusao\": null, \"ativo\": true}"))
                .andExpect(status().isOk());
    }
}