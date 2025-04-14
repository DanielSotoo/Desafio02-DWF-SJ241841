package com.restaurante.saborgourmet.controller;

import com.restaurante.saborgourmet.dto.PedidoDTO;
import com.restaurante.saborgourmet.model.EstadoPedido;
import com.restaurante.saborgourmet.model.Pedido;
import com.restaurante.saborgourmet.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getById(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getByClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.getByClienteId(clienteId));
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@Valid @RequestBody PedidoDTO pedidoDTO) {
        return new ResponseEntity<>(pedidoService.create(pedidoDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> updateEstado(@PathVariable Long id, @RequestParam EstadoPedido estado) {
        return ResponseEntity.ok(pedidoService.updateEstado(id, estado));
    }
}