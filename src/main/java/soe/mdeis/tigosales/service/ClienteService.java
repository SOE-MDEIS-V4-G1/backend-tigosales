package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.ClienteDto;
import soe.mdeis.tigosales.model.Cliente;
import soe.mdeis.tigosales.model.Ruta;
import soe.mdeis.tigosales.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private RutaService rutaService;

    public List<Cliente> list() {
        return clienteRepositorio.findAll();
    }

    public List<Cliente> findByRuta(long id_ruta) throws Exception {
        Optional<Ruta> ruta = rutaService.findById(id_ruta);
        if (ruta.isEmpty()) {
            throw new Exception("No se encontró la ruta");
        }
        return clienteRepositorio.findByRuta(ruta.get());
    }

    public Optional<Cliente> findByCi(String ci) {
        return clienteRepositorio.findByCi(ci);
    }

    public Optional<Cliente> findById(long id) {
        return clienteRepositorio.findById(id);
    }

    public boolean existByCi(String ci) {
        return !clienteRepositorio.findByCi(ci).isEmpty();
    }

    public boolean existById(long id) {
        return !clienteRepositorio.findById(id).isEmpty();
    }

    public Cliente save(ClienteDto clientedDto) {
        Cliente cliente = new Cliente();
        cliente.setCi(clientedDto.getCi());
        cliente.setNombre(clientedDto.getNombre());
        cliente.setApellido(clientedDto.getApellido());
        cliente.setDireccion(clientedDto.getDireccion());
        cliente.setTelefono(clientedDto.getTelefono());
        if (clientedDto.getRuta() != 0) {
            Optional<Ruta> ruta = rutaService.findById(clientedDto.getRuta());
            cliente.setRuta(ruta.get());
        }
        return clienteRepositorio.save(cliente);
    }

    public Cliente update(Long id, ClienteDto clientedDto) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setCi(clientedDto.getCi());
        cliente.setNombre(clientedDto.getNombre());
        cliente.setApellido(clientedDto.getApellido());
        cliente.setDireccion(clientedDto.getDireccion());
        cliente.setTelefono(clientedDto.getTelefono());
        if (clientedDto.getRuta() != 0) {
            Optional<Ruta> ruta = rutaService.findById(clientedDto.getRuta());
            cliente.setRuta(ruta.get());
        }
        return clienteRepositorio.save(cliente);
    }

    public void delete(long id) {
        clienteRepositorio.deleteById(id);
    }
}
