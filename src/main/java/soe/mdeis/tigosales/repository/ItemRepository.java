package soe.mdeis.tigosales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.tigosales.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsById(long id);

    Optional<Item> findOneByNombre(String nombre);

    Optional<Item> findById(long id);

}
