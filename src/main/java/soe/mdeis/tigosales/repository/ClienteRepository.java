package soe.mdeis.tigosales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soe.mdeis.tigosales.model.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNombre(String nombre);

    boolean existsById(long id);

    Optional<Cliente> findByCi(String ci);

}
