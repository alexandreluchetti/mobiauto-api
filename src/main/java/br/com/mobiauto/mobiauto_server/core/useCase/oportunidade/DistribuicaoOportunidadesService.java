package br.com.mobiauto.mobiauto_server.core.useCase.oportunidade;

import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.OportunidadeRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DistribuicaoOportunidadesService {

    private final UsuarioRepository usuarioRepository;
    private final OportunidadeRepository oportunidadeRepository;

    @Autowired
    public DistribuicaoOportunidadesService(UsuarioRepository usuarioRepository, OportunidadeRepository oportunidadeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.oportunidadeRepository = oportunidadeRepository;
    }

    public Optional<Usuario> findNextAssistente(Long revendaId) {
        List<Usuario> assistentes = usuarioRepository.findByRevendaIdAndCargo(revendaId, "ASSISTENTE");

        return assistentes.stream()
                .sorted((a1, a2) -> {
                    int compare = Integer.compare(
                            getOportunidadesEmAndamento(a1.getId()).size(),
                            getOportunidadesEmAndamento(a2.getId()).size()
                    );
                    if (compare == 0) {
                        return getUltimaAtribuicao(a1.getId()).compareTo(getUltimaAtribuicao(a2.getId()));
                    }
                    return compare;
                })
                .findFirst();
    }

    private List<Oportunidade> getOportunidadesEmAndamento(Long usuarioId) {
        return oportunidadeRepository.findByUsuarioIdAndStatus(usuarioId, "EM_ATENDIMENTO");
    }

    private Date getUltimaAtribuicao(Long usuarioId) {
        return oportunidadeRepository.findUltimaAtribuicaoByUsuarioId(usuarioId)
                .orElse(Date.valueOf(LocalDate.MIN));
    }

    public Oportunidade distribuirOportunidade(Oportunidade oportunidade) {
        if (oportunidade.getUsuario() == null) {
            Optional<Usuario> assistente = findNextAssistente(oportunidade.getRevenda().getId());
            assistente.ifPresent(oportunidade::setUsuario);
            oportunidade.setDataAtribuicao(Date.valueOf(LocalDate.now()));
            return oportunidade;
        }
        return null;
    }
}