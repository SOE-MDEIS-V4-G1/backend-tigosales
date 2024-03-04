package soe.mdeis.tigosales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.UsuarioDto;
import soe.mdeis.tigosales.enums.Enums.TipoUsuario;
import soe.mdeis.tigosales.model.Ruta;
import soe.mdeis.tigosales.model.Usuario;
import soe.mdeis.tigosales.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutaService rutaService;

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findOneByCi(String ci) {
        return usuarioRepository.findOneByCi(ci);
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findOneByUserAndPassword(String user, String password) {
        return usuarioRepository.findOneByUserAndPassword(user, password);
    }

    public boolean existByCi(String ci) {
        return !usuarioRepository.findOneByCi(ci).isEmpty();
    }

    public boolean existById(long id) {
        return !usuarioRepository.findById(id).isEmpty();
    }

    public Usuario save(UsuarioDto usuarioDto) throws Exception {
        Optional<Ruta> ruta = rutaService.findById(usuarioDto.getRuta());
        if (ruta.isEmpty()) {
            throw new Exception("El id de la ruta no es valido");
        }
        Usuario usuario = new Usuario();
        usuario.setCi(usuarioDto.getCi());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUser(usuarioDto.getUser());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setTipoUsuario(TipoUsuario.valueOf(usuarioDto.getTipo().toUpperCase()));
        usuario.setRuta(ruta.get());
        usuario.setActivo(usuarioDto.isActivo());
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, UsuarioDto usuarioDto) throws Exception {
        Optional<Ruta> ruta = rutaService.findById(usuarioDto.getRuta());
        if (ruta.isEmpty()) {
            throw new Exception("El id de la ruta no es valido");
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setCi(usuarioDto.getCi());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUser(usuarioDto.getUser());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setTipoUsuario(TipoUsuario.valueOf(usuarioDto.getTipo().toUpperCase()));
        usuario.setActivo(usuarioDto.isActivo());
        usuario.setRuta(ruta.get());
        return usuarioRepository.save(usuario);
    }

    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }
}
