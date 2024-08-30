package br.com.mobiauto.mobiauto_server.dataprovider.repositorios;

import br.com.mobiauto.mobiauto_server.core.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
