package br.com.mobiauto.mobiauto_server.dataprovider.repositorios;

import br.com.mobiauto.mobiauto_server.core.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
