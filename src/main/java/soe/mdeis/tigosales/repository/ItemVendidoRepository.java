package soe.mdeis.tigosales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.tigosales.model.ItemVendido;

public interface ItemVendidoRepository extends JpaRepository<ItemVendido, Long> {

    boolean existsById(long id);

    Optional<ItemVendido> findById(long id);

}
