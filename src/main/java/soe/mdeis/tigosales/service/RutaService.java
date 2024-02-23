package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.RutaDto;
import soe.mdeis.tigosales.model.Ruta;
import soe.mdeis.tigosales.model.Sucursal;
import soe.mdeis.tigosales.repository.RutaRepository;
import soe.mdeis.tigosales.repository.SucursalRepository;

@Service
@Transactional
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public Optional<Ruta> findById(long id) {
        return rutaRepository.findById(id);
    }

    public List<Ruta> getAll() {
        return rutaRepository.findAll();
    }

    public List<Ruta> getBySucursal(long sucursal_id) throws Exception {
        Optional<Sucursal> sucursal = sucursalRepository.findById(sucursal_id);
        if (sucursal.isEmpty()) {
            throw new Exception("No se ha encontrado la sucursal");
        }
        return rutaRepository.findBySucursal(sucursal.get());
    }

    public Ruta save(RutaDto rutaDto) throws Exception {
        Optional<Sucursal> sucursal = sucursalRepository.findById(rutaDto.getSucursal());
        if (sucursal.isEmpty()) {
            throw new Exception("Sucursal no encontrada");
        }
        Ruta ruta = new Ruta();
        ruta.setSucursal(sucursal.get());
        ruta.setNombre(rutaDto.getNombre());
        rutaRepository.save(ruta);
        return ruta;
    }

    public Ruta update(long id, RutaDto rutaDto) throws Exception {
        Optional<Sucursal> sucursal = sucursalRepository.findById(rutaDto.getSucursal());
        if (sucursal.isEmpty()) {
            throw new Exception("Sucursal no encontrada");
        }
        Ruta ruta = new Ruta();
        ruta.setId(id);
        ruta.setNombre(rutaDto.getNombre());
        ruta.setSucursal(sucursal.get());
        rutaRepository.save(ruta);
        return ruta;
    }

    public void delete(long id) {
        rutaRepository.deleteById(id);
    }

    public boolean existById(long id) {
        return !rutaRepository.findById(id).isEmpty();
    }
}
