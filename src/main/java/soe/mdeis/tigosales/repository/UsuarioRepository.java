package soe.mdeis.tigosales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soe.mdeis.tigosales.model.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);

    boolean existsById(long id);

    Optional<Usuario> findByCi(String ci);

}
