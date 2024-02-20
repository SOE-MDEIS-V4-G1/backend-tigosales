package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.UsuarioDto;
import soe.mdeis.tigosales.enums.Enums.TipoUsuario;
import soe.mdeis.tigosales.model.entity.Usuario;
import soe.mdeis.tigosales.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findOneByCi(String ci) {
        return usuarioRepository.findOneByCi(ci);
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    public boolean existByCi(String ci) {
        return !usuarioRepository.findOneByCi(ci).isEmpty();
    }

    public boolean existById(long id) {
        return !usuarioRepository.findById(id).isEmpty();
    }

    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setCi(usuarioDto.getCi());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUser(usuarioDto.getUser());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setTipoUsuario(TipoUsuario.valueOf(usuarioDto.getTipo().toUpperCase()));
        usuario.setActivo(usuarioDto.isActivo());
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setCi(usuarioDto.getCi());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUser(usuarioDto.getUser());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setTipoUsuario(TipoUsuario.valueOf(usuarioDto.getTipo().toUpperCase()));
        usuario.setActivo(usuarioDto.isActivo());
        return usuarioRepository.save(usuario);
    }

    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }
}
