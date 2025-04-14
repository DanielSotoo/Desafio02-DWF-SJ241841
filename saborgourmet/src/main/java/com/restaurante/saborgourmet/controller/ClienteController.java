package com.restaurante.saborgourmet.controller;

import com.restaurante.saborgourmet.dto.ClienteDTO;
import com.restaurante.saborgourmet.model.Cliente;
import com.restaurante.saborgourmet.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.create(clienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.update(id, clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}