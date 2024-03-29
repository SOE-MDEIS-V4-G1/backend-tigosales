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
import soe.mdeis.tigosales.dto.RutaDto;
import soe.mdeis.tigosales.model.Ruta;
import soe.mdeis.tigosales.service.RutaService;

@RestController
@RequestMapping("/ruta")
@CrossOrigin(origins = "*")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @GetMapping("/")
    public ResponseEntity<List<Ruta>> getAll() {
        List<Ruta> list = rutaService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        if (!rutaService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Ruta ruta = rutaService.findById(id).get();
        return new ResponseEntity<>(ruta, HttpStatus.OK);
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getBySucursal(@PathVariable("id") Long id) {
        try {
            List<Ruta> lista = rutaService.getBySucursal(id);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se ha encontrado la Sucursal");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RutaDto rutaDto) {
        if (StringUtils.isBlank(rutaDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }

        Ruta ruta;
        try {
            ruta = rutaService.save(rutaDto);
            return ResponseEntity.ok(ruta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("La sucursal indicada no es válida");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RutaDto rutaDto) {
        if (StringUtils.isBlank(rutaDto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre es Obligatorio");
        }
        try {
            Ruta updatedRuta = rutaService.update(id, rutaDto);
            if (updatedRuta != null) {
                return ResponseEntity.ok(updatedRuta);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().body("La sucursal indicada no es valida");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        rutaService.delete(id);
        return ResponseEntity.ok().body("Ruta Eliminada exitosamente");
    }
}
