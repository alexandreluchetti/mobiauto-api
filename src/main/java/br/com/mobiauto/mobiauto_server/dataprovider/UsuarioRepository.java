package br.com.mobiauto.mobiauto_server.dataprovider;

import br.com.mobiauto.mobiauto_server.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.revenda.id = :revendaId AND u.cargo = :cargo")
    List<Usuario> findByRevendaIdAndCargo(Long revendaId, String cargo);
}
