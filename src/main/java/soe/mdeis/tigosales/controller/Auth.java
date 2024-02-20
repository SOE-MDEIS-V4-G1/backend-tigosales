package soe.mdeis.tigosales.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.tigosales.dto.LoginDto;
import soe.mdeis.tigosales.model.entity.Usuario;
import soe.mdeis.tigosales.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth/")
@CrossOrigin(origins = "*")
public class Auth {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginDto login) {
        if (StringUtils.isBlank(login.getUser())) {
            return ResponseEntity.badRequest().body("falta user");
        }
        if (StringUtils.isBlank(login.getPassword())) {
            return ResponseEntity.badRequest().body("Falta password");
        }
        Optional<Usuario> usuario = usuarioService.findOneByUserAndPassword(login.getUser(), login.getPassword());
        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no Válido");
        }
        return ResponseEntity.ok(usuario.get());
    }

}
