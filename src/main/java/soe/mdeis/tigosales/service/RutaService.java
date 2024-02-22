package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.RutaDto;
import soe.mdeis.tigosales.model.Ruta;
import soe.mdeis.tigosales.repository.RutaRepository;

@Service
@Transactional
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public Optional<Ruta> findById(long id) {
        return rutaRepository.findById(id);
    }

    public List<Ruta> getAll() {
        return rutaRepository.findAll();
    }

    public Ruta save(RutaDto rutaDto) {
        Ruta ruta = new Ruta();
        ruta.setNombre(rutaDto.getNombre());
        rutaRepository.save(ruta);
        return ruta;
    }

    public Ruta update(long id, RutaDto rutaDto) {
        Ruta ruta = new Ruta();
        ruta.setId(id);
        ruta.setNombre(rutaDto.getNombre());
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
