package com.restaurante.saborgourmet.controller;

import com.restaurante.saborgourmet.dto.ProductoDTO;
import com.restaurante.saborgourmet.model.Producto;
import com.restaurante.saborgourmet.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Producto> create(@Valid @RequestBody ProductoDTO productoDTO) {
        return new ResponseEntity<>(productoService.create(productoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.update(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}