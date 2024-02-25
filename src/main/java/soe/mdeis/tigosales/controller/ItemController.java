package soe.mdeis.tigosales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.tigosales.dto.ItemDto;
import soe.mdeis.tigosales.model.Item;
import soe.mdeis.tigosales.service.ItemService;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> list = itemService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        if (!itemService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Item item = itemService.findById(id).get();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ItemDto itemDto) {
        if (StringUtils.isBlank(itemDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }

        Item item = itemService.save(itemDto);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        if (StringUtils.isBlank(itemDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }

        Item updatedItem = itemService.update(id, itemDto);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.ok().body("Item eliminado exitosamente");
    }
}
