package soe.mdeis.tigosales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.tigosales.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    boolean existsById(long id);

    Optional<Venta> findById(long id);

}
