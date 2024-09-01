package br.com.mobiauto.mobiauto_server.dataprovider;

import br.com.mobiauto.mobiauto_server.core.entity.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevendaRepository extends JpaRepository<Revenda, Long> {
}
