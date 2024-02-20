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
import soe.mdeis.tigosales.dto.SucursalDto;
import soe.mdeis.tigosales.model.Sucursal;
import soe.mdeis.tigosales.service.SucursalService;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin(origins = "*")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/")
    public ResponseEntity<List<Sucursal>> getAll() {
        List<Sucursal> list = sucursalService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!sucursalService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Sucursal sucursal = sucursalService.findById(id).get();
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SucursalDto sucursalDto) {
        if (StringUtils.isBlank(sucursalDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }

        Sucursal sucursal = sucursalService.save(sucursalDto);
        return ResponseEntity.ok(sucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SucursalDto sucursalDto) {
        if (StringUtils.isBlank(sucursalDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }
        Sucursal updatedSucursal = sucursalService.update(id, sucursalDto);
        if (updatedSucursal != null) {
            return ResponseEntity.ok(updatedSucursal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.ok().body("Sucursal Eliminada exitosamente");
    }
}
