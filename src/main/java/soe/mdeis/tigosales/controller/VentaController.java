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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.tigosales.dto.VentaDto;
import soe.mdeis.tigosales.model.Venta;
import soe.mdeis.tigosales.service.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    public ResponseEntity<List<Venta>> getAll() {
        List<Venta> list = ventaService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        if (!ventaService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Venta venta = ventaService.findById(id).get();
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaDto ventaDto) {

        Venta venta;
        try {
            venta = ventaService.save(ventaDto);
            return ResponseEntity.ok(venta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en Venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ventaService.delete(id);
        return ResponseEntity.ok().body("Item eliminado exitosamente");
    }
}
