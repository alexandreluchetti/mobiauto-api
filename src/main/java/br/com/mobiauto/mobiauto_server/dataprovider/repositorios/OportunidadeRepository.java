package br.com.mobiauto.mobiauto_server.dataprovider.repositorios;

import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {
}
