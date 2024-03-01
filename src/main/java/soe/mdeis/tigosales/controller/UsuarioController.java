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
import soe.mdeis.tigosales.dto.UsuarioDto;
import soe.mdeis.tigosales.model.Usuario;
import soe.mdeis.tigosales.service.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!usuarioService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioService.findById(id).get();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/ci")
    public ResponseEntity<?> getClienteByCi(@RequestParam("ci") String ci) {
        Optional<Usuario> usuario = usuarioService.findOneByCi(ci);
        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
        if (StringUtils.isBlank(usuarioDto.getCi())) {
            return ResponseEntity.badRequest().body("El ci es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getApellido())) {
            return ResponseEntity.badRequest().body("El apellido es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getUser())) {
            return ResponseEntity.badRequest().body("el user es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getPassword())) {
            return ResponseEntity.badRequest().body("El password es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getTipo())) {
            return ResponseEntity.badRequest().body("El tipo es obligatorio");
        }
        if (!usuarioDto.getTipo().toLowerCase().equals("admin")
                && !usuarioDto.getTipo().toLowerCase().equals("vendedor")) {
            return ResponseEntity.badRequest().body("El tipo solo puede ser: admin, vendedor");
        }
        if (usuarioService.existByCi(usuarioDto.getCi())) {
            return ResponseEntity.badRequest().body("El CI ya existe en la base de datos");
        }

        try {
            Usuario usuario = usuarioService.save(usuarioDto);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo Registrar " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        if (StringUtils.isBlank(usuarioDto.getCi())) {
            return ResponseEntity.badRequest().body("El ci es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getApellido())) {
            return ResponseEntity.badRequest().body("El apellido es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getUser())) {
            return ResponseEntity.badRequest().body("el user es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getPassword())) {
            return ResponseEntity.badRequest().body("El password es Obligatorio");
        }
        if (StringUtils.isBlank(usuarioDto.getTipo())) {
            return ResponseEntity.badRequest().body("El tipo es obligatorio");
        }
        if (!usuarioDto.getTipo().toLowerCase().equals("admin")
                && !usuarioDto.getTipo().toLowerCase().equals("vendedor")) {
            return ResponseEntity.badRequest().body("El tipo solo puede ser: admin, vendedor");
        }
        if (usuarioService.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("No existe usuario con id " + id);
        }
        Usuario updatedUsuario;
        try {
            updatedUsuario = usuarioService.update(id, usuarioDto);
            if (updatedUsuario != null) {
                return ResponseEntity.ok(updatedUsuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo Registrar " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        if (!usuarioService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.delete(id);
        return ResponseEntity.ok().body("Usuario eliminado exitosamente");
    }
}
