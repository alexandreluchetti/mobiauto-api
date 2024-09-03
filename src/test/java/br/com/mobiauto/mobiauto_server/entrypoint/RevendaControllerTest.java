package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.dataprovider.RevendaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RevendaControllerTest {

    private static final String ADMIN_EMAIL = "admin@mobiauto.com";
    private static final String ADMIN_ROLE = "ADMINISTRADOR";
    private static final String PROPRIETARIO_EMAIL = "teste_proprietario@mobiauto.com";
    private static final String PROPRIETARIO_ROLE = "GERENTE";
    private static final String ASSISTENTE_EMAIL = "teste_assistente@mobiauto.com";
    private static final String ASSISTENTE_ROLE = "GERENTE";

    private final MockMvc mockMvc;
    private final RevendaRepository revendaRepository;

    @Autowired
    public RevendaControllerTest(MockMvc mockMvc, RevendaRepository revendaRepository) {
        this.mockMvc = mockMvc;
        this.revendaRepository = revendaRepository;
    }

//TODO Alterar CNPJ para evitar o registro duplicado

//    @Test
//    @WithMockUser(roles = "ADMINISTRADOR")
//    public void testAdminPodeCriarRevenda() throws Exception {
//        mockMvc.perform(post("/revendas")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"cnpj\": \"79318073000186\", \"nomeSocial\": \"Nova Revenda\", \"ativo\": true}"))
//                .andExpect(status().isCreated());
//    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
    public void testAdminPodeAtualizarRevenda() throws Exception {
        mockMvc.perform(put("/revendas/8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cnpj\": \"79318073000186\", \"nomeSocial\": \"Revenda Atualizada\", \"ativo\": false}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
    public void testAdminPodeBuscarRevendaPeloId() throws Exception {
        mockMvc.perform(get("/revendas/8"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
    public void testAdminPodeBuscarTodasRevendas() throws Exception {
        mockMvc.perform(get("/revendas"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = PROPRIETARIO_EMAIL, roles = PROPRIETARIO_ROLE)
    public void testProprietarioPodeBuscarSuaRevendaPeloId() throws Exception {
        mockMvc.perform(get("/revendas/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = PROPRIETARIO_EMAIL, roles = PROPRIETARIO_ROLE)
    public void testProprietarioNaoPodeBuscarOutraRevendaPeloId() throws Exception {
        mockMvc.perform(get("/revendas/8"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = PROPRIETARIO_EMAIL, roles = PROPRIETARIO_ROLE)
    public void testProprietarioCannotCreateRevenda() throws Exception {
        mockMvc.perform(post("/revendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cnpj\": \"98765432109876\", \"nomeSocial\": \"Nova Revenda\", \"ativo\": true}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = ASSISTENTE_EMAIL, roles = ASSISTENTE_ROLE)
    public void testAssistenteCanGetAllRevendas() throws Exception {
        mockMvc.perform(get("/revendas"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = ASSISTENTE_EMAIL, roles = ASSISTENTE_ROLE)
    public void testAssistenteNaoPodeAtualizarRevenda() throws Exception {
        mockMvc.perform(put("/revendas/8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cnpj\": \"12345678901234\", \"nomeSocial\": \"Revenda Atualizada\", \"ativo\": true}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = ASSISTENTE_EMAIL, roles = ASSISTENTE_ROLE)
    public void testAssistenteCannotDeleteRevenda() throws Exception {
        mockMvc.perform(delete("/revendas/8"))
                .andExpect(status().isForbidden());
    }
}
