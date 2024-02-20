package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.SucursalDto;
import soe.mdeis.tigosales.model.Sucursal;
import soe.mdeis.tigosales.repository.SucursalRepository;

@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public Optional<Sucursal> findById(long id) {
        return sucursalRepository.findById(id);
    }

    public List<Sucursal> getAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal save(SucursalDto sucursalDto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalDto.getNombre());
        sucursalRepository.save(sucursal);
        return sucursal;
    }

    public Sucursal update(long id, SucursalDto sucursalDto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(id);
        sucursal.setNombre(sucursalDto.getNombre());
        sucursalRepository.save(sucursal);
        return sucursal;
    }

    public void delete(long id) {
        sucursalRepository.deleteById(id);
    }

    public boolean existById(long id) {
        return !sucursalRepository.findById(id).isEmpty();
    }
}
