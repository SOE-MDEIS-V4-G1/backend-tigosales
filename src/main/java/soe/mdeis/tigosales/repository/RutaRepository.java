package soe.mdeis.tigosales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.tigosales.model.Ruta;
import soe.mdeis.tigosales.model.Sucursal;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {

    boolean existsById(long id);

    Optional<Ruta> findOneByNombre(String nombre);

    List<Ruta> findBySucursal(Sucursal sucursal);

}
