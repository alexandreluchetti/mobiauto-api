package br.com.mobiauto.mobiauto_server.core.useCase;

import br.com.mobiauto.mobiauto_server.configuration.exception.NoneResultException;
import br.com.mobiauto.mobiauto_server.configuration.exception.UnauthorizedException;
import br.com.mobiauto.mobiauto_server.core.entity.Revenda;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.RevendaRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RevendaService {

    private final RevendaRepository revendaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RevendaService(RevendaRepository revendaRepository, UsuarioRepository usuarioRepository) {
        this.revendaRepository = revendaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Revenda> findAll() {
        return revendaRepository.findAll();
    }

    public Optional<Revenda> findById(Long id) throws UnauthorizedException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Usuario> usuarioAutenticadoOpt = usuarioRepository.findByEmail(userDetails.getUsername());

        if (usuarioAutenticadoOpt.isPresent()) {
            Usuario usuarioAutenticado = usuarioAutenticadoOpt.get();

            if (usuarioAutenticado.getCargo().isAdministrador()) {
                return getRevenda(id);
            } else if (usuarioAutenticado.getRevenda().getId().equals(id)) {
                return getRevenda(id);
            }
        }

        throw new UnauthorizedException("Usuário não autorizado a acessar esta loja.");
    }

    private Optional<Revenda> getRevenda(Long id) {
        Revenda revenda = revendaRepository.findById(id)
                .orElseThrow(() -> new NoneResultException("Loja não encontrada"));
        return Optional.of(revenda);
    }

    public Revenda save(Revenda revenda) {
        return revendaRepository.save(revenda);
    }

    public void deleteById(Long id) {
        revendaRepository.deleteById(id);
    }
}
