package br.com.mobiauto.mobiauto_server.core.useCase;

import br.com.mobiauto.mobiauto_server.configuration.exception.OperationException;
import br.com.mobiauto.mobiauto_server.configuration.exception.UnauthorizedException;
import br.com.mobiauto.mobiauto_server.core.entity.Cargo;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.RevendaRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RevendaRepository revendaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RevendaRepository revendaRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.revendaRepository = revendaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) throws UnauthorizedException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Usuario> usuarioAutenticadoOpt = usuarioRepository.findByEmail(userDetails.getUsername());

        if (usuarioAutenticadoOpt.isPresent()) {
            Usuario usuarioAutenticado = usuarioAutenticadoOpt.get();

            if (usuarioAutenticado.getCargo().isAdministrador()) {
                return saveUsuario(usuario);
            } else if (usuarioAutenticado.getCargo().isProprietarioOuGerente() && usuarioAutenticado.getRevenda().getId().equals(usuario.getRevenda().getId())) {
                return saveUsuario(usuario);
            }
        }

        throw new UnauthorizedException("Usuário não autorizado a realizar esta operação.");
    }

    private Usuario saveUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioAtualizado) throws UnauthorizedException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Usuario> usuarioAutenticadoOpt = usuarioRepository.findByEmail(userDetails.getUsername());

        if (usuarioAutenticadoOpt.isPresent()) {
            Usuario usuarioAutenticado = usuarioAutenticadoOpt.get();

            if (usuarioAutenticado.getCargo().isAdministrador() || (usuarioAutenticado.getCargo().isProprietario() && usuarioAutenticado.getRevenda().getId().equals(usuarioAtualizado.getRevenda().getId()))) {

                usuarioAtualizado.setId(id);
                usuarioAtualizado.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
                return usuarioRepository.save(usuarioAtualizado);
            }
        }

        throw new UnauthorizedException("Usuário não autorizado a realizar esta operação.");
    }
}
