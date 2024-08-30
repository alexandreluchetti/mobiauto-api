package br.com.mobiauto.mobiauto_server.configuration.shared;

import br.com.mobiauto.mobiauto_server.core.entity.Cargo;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createDefaultAdmin() {
        return args -> {
            if (usuarioRepository.findByEmail("admin@mobiauto.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@mobiauto.com");
                admin.setSenha(passwordEncoder.encode("123456"));
                admin.setCargo(Cargo.ADMINISTRADOR);
                usuarioRepository.save(admin);
                System.out.println("Usuário administrador padrão criado com sucesso!");
            } else {
                System.out.println("Usuário administrador já existe.");
            }
        };
    }
}
