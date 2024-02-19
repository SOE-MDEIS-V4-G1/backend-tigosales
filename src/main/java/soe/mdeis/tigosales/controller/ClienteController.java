package soe.mdeis.tigosales.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.tigosales.dto.ClienteDto;
import soe.mdeis.tigosales.model.entity.Cliente;
import soe.mdeis.tigosales.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> list = clienteService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!clienteService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteService.findById(id).get();
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/ci")
    public ResponseEntity<?> getClienteByCi(@RequestParam("ci") String ci) {
        Optional<Cliente> cliente = clienteService.findByCi(ci);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        if (StringUtils.isBlank(clienteDto.getCi())) {
            return ResponseEntity.badRequest().body("El ci es Obligatorio");
        }
        if (StringUtils.isBlank(clienteDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }
        if (StringUtils.isBlank(clienteDto.getApellido())) {
            return ResponseEntity.badRequest().body("El apellido es Obligatorio");
        }
        if (StringUtils.isBlank(clienteDto.getDireccion())) {
            return ResponseEntity.badRequest().body("La dirección es Obligatoria");
        }
        if (StringUtils.isBlank(clienteDto.getNombre())) {
            return ResponseEntity.badRequest().body("El teléfono es Obligatorio");
        }
        if (clienteService.existByCi(clienteDto.getCi())) {
            return ResponseEntity.badRequest().body("El CI ya existe en la base de datos");
        }
        Cliente cliente = clienteService.save(clienteDto);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        if (StringUtils.isBlank(clienteDto.getCi())) {
            return ResponseEntity.badRequest().body("El ci es Obligatorio");
        }
        if (StringUtils.isBlank(clienteDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }
        if (StringUtils.isBlank(clienteDto.getApellido())) {
            return ResponseEntity.badRequest().body("El apellido es Obligatorio");
        }
        if (StringUtils.isBlank(clienteDto.getDireccion())) {
            return ResponseEntity.badRequest().body("La dirección es Obligatoria");
        }
        if (StringUtils.isBlank(clienteDto.getNombre())) {
            return ResponseEntity.badRequest().body("El teléfono es Obligatorio");
        }
        Cliente updatedCliente = clienteService.update(id, clienteDto);
        if (updatedCliente != null) {
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().body("Cliente Eliminado exitosamente");
    }
}
