package br.com.mobiauto.mobiauto_server.core.useCase.oportunidade;

import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.OportunidadeRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DistribuicaoOportunidadesService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    public Optional<Usuario> findNextAssistente(Long revendaId) {
        // Busca todos os assistentes da revenda
        List<Usuario> assistentes = usuarioRepository.findByRevendaIdAndCargo(revendaId, "ASSISTENTE");

        // Ordena os assistentes por menor quantidade de oportunidades em andamento e maior tempo sem receber uma oportunidade
        return assistentes.stream()
                .sorted((a1, a2) -> {
                    int compare = Integer.compare(
                            getOportunidadesEmAndamento(a1.getId()).size(),
                            getOportunidadesEmAndamento(a2.getId()).size()
                    );
                    if (compare == 0) {
                        // Se ambos têm o mesmo número de oportunidades, ordenar pelo maior tempo sem receber uma oportunidade
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