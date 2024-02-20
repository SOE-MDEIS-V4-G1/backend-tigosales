package soe.mdeis.tigosales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.tigosales.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    boolean existsById(long id);

    Optional<Sucursal> findOneByNombre(String nombre);

}
