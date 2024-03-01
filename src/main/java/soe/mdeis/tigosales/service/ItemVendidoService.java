package soe.mdeis.tigosales.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.ItemVendidoDto;
import soe.mdeis.tigosales.model.Item;
import soe.mdeis.tigosales.model.ItemVendido;
import soe.mdeis.tigosales.model.Venta;
import soe.mdeis.tigosales.repository.ItemVendidoRepository;

@Service
@Transactional
public class ItemVendidoService {

    @Autowired
    private ItemVendidoRepository itemRepository;

    public Optional<ItemVendido> findById(long id) {
        return itemRepository.findById(id);
    }

    public List<ItemVendido> getAll() {
        return itemRepository.findAll();
    }

    public ItemVendido save(ItemVendidoDto itemDto, Venta venta) {
        ItemVendido itemVendido = new ItemVendido();
        itemVendido.setCantidad(itemDto.getCantidad());
        Item item = new Item();
        item.setId(itemDto.getId());
        itemVendido.setItem(item);
        itemVendido.setPrecioUnitario(BigDecimal.valueOf(itemDto.getPrecio()));
        itemVendido.setVenta(venta);
        return itemRepository.save(itemVendido);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    public boolean existById(long id) {
        return !itemRepository.findById(id).isEmpty();
    }
}
