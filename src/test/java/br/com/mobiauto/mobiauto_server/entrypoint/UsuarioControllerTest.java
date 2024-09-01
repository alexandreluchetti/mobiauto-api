package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.core.entity.Cargo;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.RevendaRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    private static final String ADMIN_EMAIL = "admin@mobiauto.com";
    private static final String ADMIN_ROLE = "ADMINISTRADOR";
    private static final String GERENTE_EMAIL = "teste_gerente@mobiauto.com";
    private static final String GERENTE_ROLE = "GERENTE";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RevendaRepository revendaRepository;

//TODO Alterar CNPJ para evitar o registro duplicado

//    @Test
//    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
//    public void testAdminCanPostToUsuarios() throws Exception {
//        Revenda revenda = new Revenda();
//        revenda.setCnpj("28028921000181");
//        revenda.setNomeSocial("Revenda Teste");
//        revenda.setAtivo(true);
//        revenda = revendaRepository.save(revenda);
//
//        mockMvc.perform(post("/usuarios")
//                        .contentType("application/json")
//                        .content("{\"nome\": \"Novo Usuario\", \"email\": \"novo@teste.com\", \"senha\": \"123456\", \"cargo\": \"GERENTE\", \"revenda\": {\"id\": " + revenda.getId() + "}}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.nome", is("Novo Usuario")))
//                .andExpect(jsonPath("$.email", is("novo@teste.com")));
//    }

    @Test
    @WithMockUser(username = GERENTE_EMAIL, roles = GERENTE_ROLE)
    public void testGerenteCanNotPostToUsuariosFromDiferentRevenda() throws Exception {
        mockMvc.perform(post("/usuarios")
                        .contentType("application/json")
                        .content("{\"nome\": \"Novo Usuario\", \"email\": \"novo@teste.com\", \"senha\": \"123456\", \"cargo\": \"GERENTE\", \"revenda\": {\"id\": 6}}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
    public void testAdminCanGetUsuarios() throws Exception {
        mockMvc.perform(get("/usuarios/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Usuario Atualizado")))
                .andExpect(jsonPath("$.email", is("atualizado@teste.com")));
    }

    @Test
    @WithMockUser(username = GERENTE_EMAIL, roles = GERENTE_ROLE)
    public void testGerenteCanGetUsuarios() throws Exception {
        mockMvc.perform(get("/usuarios/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Usuario Atualizado")))
                .andExpect(jsonPath("$.email", is("atualizado@teste.com")));
    }

    @Test
    @WithMockUser(roles = "ASSISTENTE")
    public void testAssistenteCanNotPostToUsuarios() throws Exception {
        mockMvc.perform(post("/usuarios")
                        .contentType("application/json")
                        .content("{\"nome\": \"Novo Usuario\", \"email\": \"novo@teste.com\", \"senha\": \"123456\", \"cargo\": \"GERENTE\", \"revenda\": {\"id\": 1}}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
    public void testAdminCanUpdateUsuario() throws Exception {
        mockMvc.perform(put("/usuarios/7")
                        .contentType("application/json")
                        .content("{\"nome\": \"Usuario Atualizado\", \"email\": \"atualizado@teste.com\", \"senha\": \"654321\", \"cargo\": \"GERENTE\", \"revenda\": {\"id\": 6}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Usuario Atualizado")))
                .andExpect(jsonPath("$.email", is("atualizado@teste.com")));
    }

    @Test
    @WithMockUser(roles = "PROPRIETARIO")
    public void testProprietarioCanNotUpdateUsuarioFromAnotherRevenda() throws Exception {
        mockMvc.perform(put("/usuarios/7")
                        .contentType("application/json")
                        .content("{\"nome\": \"Usuario Atualizado\", \"email\": \"atualizado@teste.com\", \"senha\": \"654321\", \"cargo\": \"GERENTE\", \"revenda\": {\"id\": 6}}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ASSISTENTE")
    public void testAssistenteCanNotUpdateUsuario() throws Exception {
        mockMvc.perform(put("/usuarios/7")
                        .contentType("application/json")
                        .content("{\"nome\": \"Usuario Atualizado\", \"email\": \"atualizado@teste.com\", \"senha\": \"654321\", \"cargo\": \"GERENTE\", \"revenda\": {\"id\": 6}}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, roles = ADMIN_ROLE)
    public void testAdminCanDeleteUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("123456");
        usuario.setCargo(Cargo.GERENTE);
        usuario.setRevenda(null);
        usuarioRepository.save(usuario);

        mockMvc.perform(delete("/usuarios/" + usuario.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = GERENTE_EMAIL, roles = GERENTE_ROLE)
    public void testGerenteCanDeleteUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("123456");
        usuario.setCargo(Cargo.GERENTE);
        usuario.setRevenda(null);
        usuarioRepository.save(usuario);

        mockMvc.perform(delete("/usuarios/" + usuario.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ASSISTENTE")
    public void testAssistenteCanNotDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/usuarios/7"))
                .andExpect(status().isForbidden());
    }
}
