package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.ClienteDto;
import soe.mdeis.tigosales.model.Cliente;
import soe.mdeis.tigosales.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepositorio;

    public List<Cliente> list() {
        return clienteRepositorio.findAll();
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
        return clienteRepositorio.save(cliente);
    }

    public void delete(long id) {
        clienteRepositorio.deleteById(id);
    }
}
