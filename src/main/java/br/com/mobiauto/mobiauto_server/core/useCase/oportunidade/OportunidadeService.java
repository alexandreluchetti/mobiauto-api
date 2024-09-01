package br.com.mobiauto.mobiauto_server.core.useCase.oportunidade;

import br.com.mobiauto.mobiauto_server.configuration.exception.UnauthorizedException;
import br.com.mobiauto.mobiauto_server.core.entity.Cargo;
import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.OportunidadeRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OportunidadeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    @Autowired
    private DistribuicaoOportunidadesService distribuicaoOportunidadesService;

    public List<Oportunidade> findAll() {
        return oportunidadeRepository.findAll();
    }

    public Optional<Oportunidade> findById(Long id) {
        return oportunidadeRepository.findById(id);
    }

//    public Oportunidade save(Oportunidade oportunidade) {
//        Optional<Usuario> usuarioOpt = getUsuarioAutenticado();
//
//        if (usuarioOpt.isPresent()) {
//            Usuario usuario = usuarioOpt.get();
//            oportunidade = distribuicaoOportunidadesService.distribuirOportunidade(oportunidade);
//
//            if (usuario.getCargo().isAdministrador()) {
//                return oportunidadeRepository.save(oportunidade);
//            } else if (usuario.getCargo().isAssistente() && oportunidade.getUsuario().getId().equals(usuario.getId())) {
//                return oportunidadeRepository.save(oportunidade);
//            } else if (usuario.getCargo().isProprietarioOuGerente() && oportunidade.getRevenda().getId().equals(usuario.getRevenda().getId())) {
//                return oportunidadeRepository.save(oportunidade);
//            }
//        }
//
//        throw new UnauthorizedException("Usuário não autorizado a realizar esta operação.");
//    }

    public void deleteById(Long id) {
        oportunidadeRepository.deleteById(id);
    }








    public Oportunidade save(Oportunidade oportunidade) {
        if (oportunidade.getUsuario() == null) {
            distribuicaoOportunidadesService.distribuirOportunidade(oportunidade);
        }
        return oportunidadeRepository.save(oportunidade);
    }

    public Oportunidade update(Long id, Oportunidade novaOportunidade) {
        Oportunidade oportunidadeExistente = oportunidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Oportunidade não encontrada"));

        Usuario usuarioAutenticado = getUsuarioAutenticado();

        if (!usuarioPodeEditarOportunidade(usuarioAutenticado, oportunidadeExistente)) {
            throw new SecurityException("Você não tem permissão para editar esta oportunidade");
        }

        novaOportunidade.update(oportunidadeExistente);
        return oportunidadeRepository.save(novaOportunidade);
    }

    public void transferirOportunidade(Long oportunidadeId, Long novoAssistenteId) {
        Oportunidade oportunidade = oportunidadeRepository.findById(oportunidadeId)
                .orElseThrow(() -> new IllegalArgumentException("Oportunidade não encontrada"));

        Usuario usuarioAutenticado = getUsuarioAutenticado();

        if (!usuarioPodeTransferirOportunidade(usuarioAutenticado, oportunidade)) {
            throw new SecurityException("Você não tem permissão para transferir esta oportunidade");
        }

        Usuario novoAssistente = usuarioRepository.findById(novoAssistenteId)
                .orElseThrow(() -> new IllegalArgumentException("Novo assistente não encontrado"));

        if (!novoAssistente.getCargo().equals(Cargo.ASSISTENTE)) {
            throw new IllegalArgumentException("O usuário escolhido não é um assistente");
        }

        oportunidade.setUsuario(novoAssistente);
        oportunidadeRepository.save(oportunidade);
    }

    private boolean usuarioPodeEditarOportunidade(Usuario usuario, Oportunidade oportunidade) {
        if (usuario.getCargo().isAssistente() && usuario.getId().equals(oportunidade.getUsuario().getId())) {
            return true;
        }
        return usuario.getCargo().isAdministrador() ||
                usuario.getCargo().isProprietarioOuGerente() ||
                usuario.getId().equals(oportunidade.getUsuario().getId());
    }

    private boolean usuarioPodeTransferirOportunidade(Usuario usuario, Oportunidade oportunidade) {
        return usuario.getCargo().isProprietarioOuGerente() && usuario.getRevenda().getId().equals(oportunidade.getRevenda().getId());
    }

    private Usuario getUsuarioAutenticado() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuário autenticado não encontrado"));
    }
}
