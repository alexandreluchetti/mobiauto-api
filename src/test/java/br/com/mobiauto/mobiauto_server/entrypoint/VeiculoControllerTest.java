package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.core.entity.Veiculo;
import br.com.mobiauto.mobiauto_server.dataprovider.VeiculoRepository;
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
public class VeiculoControllerTest {

    private final MockMvc mockMvc;
    private final VeiculoRepository veiculoRepository;

    private Veiculo veiculo;

    @Autowired
    public VeiculoControllerTest(MockMvc mockMvc, VeiculoRepository veiculoRepository) {
        this.mockMvc = mockMvc;
        this.veiculoRepository = veiculoRepository;
    }

    @BeforeEach
    public void setup() {
        // Cria um veículo de exemplo para ser usado nos testes
        veiculo = new Veiculo();
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setVersao("1.8 Flex");
        veiculo.setAnoModelo(2020);
        veiculo = veiculoRepository.save(veiculo);
    }

    @Test
    @WithMockUser
    public void testGetAllVeiculos() throws Exception {
        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca", is("Honda")))
                .andExpect(jsonPath("$[0].modelo", is("Civic")));
    }

    @Test
    @WithMockUser
    public void testGetVeiculoById() throws Exception {
        mockMvc.perform(get("/veiculos/" + veiculo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Toyota")))
                .andExpect(jsonPath("$.modelo", is("Corolla")));
    }

    @Test
    @WithMockUser
    public void testGetVeiculoByIdNotFound() throws Exception {
        mockMvc.perform(get("/veiculos/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testCreateVeiculo() throws Exception {
        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"marca\": \"Honda\", \"modelo\": \"Civic\", \"versao\": \"2.0 Sport\", \"anoModelo\": 2021}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marca", is("Honda")))
                .andExpect(jsonPath("$.modelo", is("Civic")));
    }

    @Test
    @WithMockUser
    public void testUpdateVeiculo() throws Exception {
        mockMvc.perform(put("/veiculos/" + veiculo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"marca\": \"Nissan\", \"modelo\": \"Sentra\", \"versao\": \"2.0 SL\", \"anoModelo\": 2022}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Nissan")))
                .andExpect(jsonPath("$.modelo", is("Sentra")));
    }

    @Test
    @WithMockUser
    public void testUpdateVeiculoNotFound() throws Exception {
        mockMvc.perform(put("/veiculos/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"marca\": \"Nissan\", \"modelo\": \"Sentra\", \"versao\": \"2.0 SL\", \"anoModelo\": 2022}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testDeleteVeiculo() throws Exception {
        mockMvc.perform(delete("/veiculos/" + veiculo.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void testDeleteVeiculoNotFound() throws Exception {
        mockMvc.perform(delete("/veiculos/9999"))
                .andExpect(status().isNotFound());
    }
}
