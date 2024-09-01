package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.core.entity.Cliente;
import br.com.mobiauto.mobiauto_server.dataprovider.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        // Cria um cliente de exemplo para ser usado nos testes
        cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setTelefone("123456789");
        cliente = clienteRepository.save(cliente);
    }

    @Test
    @WithMockUser
    public void testGetAllClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Cliente Teste")));
    }

    @Test
    @WithMockUser
    public void testGetClienteById() throws Exception {
        mockMvc.perform(get("/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Cliente Teste")));
    }

    @Test
    @WithMockUser
    public void testGetClienteByIdNotFound() throws Exception {
        mockMvc.perform(get("/clientes/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testCreateCliente() throws Exception {
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Novo Cliente\", \"email\": \"novo@cliente.com\", \"telefone\": \"987654321\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Novo Cliente")));
    }

    @Test
    @WithMockUser
    public void testUpdateCliente() throws Exception {
        mockMvc.perform(put("/clientes/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Cliente Atualizado\", \"email\": \"clienteatualizado@teste.com\", \"telefone\": \"987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Cliente Atualizado")));
    }

    @Test
    @WithMockUser
    public void testUpdateClienteNotFound() throws Exception {
        mockMvc.perform(put("/clientes/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Cliente Inexistente\", \"email\": \"clienteinexistente@teste.com\", \"telefone\": \"987654321\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/clientes/" + cliente.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void testDeleteClienteNotFound() throws Exception {
        mockMvc.perform(delete("/clientes/9999"))
                .andExpect(status().isNotFound());
    }
}