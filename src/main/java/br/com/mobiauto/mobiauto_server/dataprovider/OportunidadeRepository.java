package br.com.mobiauto.mobiauto_server.dataprovider;

import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

    List<Oportunidade> findByUsuarioIdAndStatus(Long usuarioId, String status);

    @Query("SELECT MAX(o.dataAtribuicao) FROM Oportunidade o WHERE o.usuario.id = :usuarioId")
    Optional<Date> findUltimaAtribuicaoByUsuarioId(Long usuarioId);
}
