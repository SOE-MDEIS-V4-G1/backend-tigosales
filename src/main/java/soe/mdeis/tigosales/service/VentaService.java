package soe.mdeis.tigosales.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.VentaDto;
import soe.mdeis.tigosales.model.Cliente;
import soe.mdeis.tigosales.model.Item;
import soe.mdeis.tigosales.model.ItemVendido;
import soe.mdeis.tigosales.model.Usuario;
import soe.mdeis.tigosales.model.Venta;
import soe.mdeis.tigosales.repository.VentaRepository;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemVendidoService itemVendidoService;

    public Optional<Venta> findById(long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    public Venta save(VentaDto ventaDto) throws Exception {
        Optional<Usuario> usuario = usuarioService.findById(ventaDto.getUsuario());
        if (usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        Optional<Cliente> cliente = clienteService.findById(ventaDto.getCliente());
        if (cliente.isEmpty()) {
            throw new Exception("Cliente no encontrado");
        }

        Venta venta = new Venta();
        venta.setItemVendidos(new ArrayList<ItemVendido>());
        venta.setMonto(BigDecimal.ZERO);
        venta.setCliente(cliente.get());
        venta.setUsuario(usuario.get());
        venta.setDate(LocalDateTime.now());
        venta.setItemVendidos(new ArrayList<>());
        ventaDto.getItems().forEach(
                item -> {
                    venta.setMonto(venta.getMonto()
                            .add(BigDecimal.valueOf(item.getPrecio())
                                    .multiply(BigDecimal.valueOf(item.getCantidad()))));
                });

        Venta salida = ventaRepository.save(venta);
        ventaDto.getItems().forEach(item -> {
            ItemVendido itemVendido = itemVendidoService.save(item, venta);
            venta.getItemVendidos().add(itemVendido);
        });
        return salida;
    }

    public void delete(long id) {
        ventaRepository.deleteById(id);
    }

    public boolean existById(long id) {
        return !ventaRepository.findById(id).isEmpty();
    }
}
