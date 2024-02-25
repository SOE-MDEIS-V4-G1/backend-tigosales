package soe.mdeis.tigosales.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soe.mdeis.tigosales.dto.ItemDto;
import soe.mdeis.tigosales.model.Item;
import soe.mdeis.tigosales.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item save(ItemDto itemDto) {
        Item item = new Item();
        item.setNombre(itemDto.getNombre());
        item.setDescripcion(itemDto.getDescripcion());
        item.setPrice(BigDecimal.valueOf(itemDto.getPrecio()));
        return itemRepository.save(item);
    }

    public Item update(long id, ItemDto itemDto) {
        Item item = new Item();
        item.setId(id);
        item.setNombre(itemDto.getNombre());
        item.setDescripcion(itemDto.getDescripcion());
        item.setPrice(BigDecimal.valueOf(itemDto.getPrecio()));
        return itemRepository.save(item);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    public boolean existById(long id) {
        return !itemRepository.findById(id).isEmpty();
    }
}
