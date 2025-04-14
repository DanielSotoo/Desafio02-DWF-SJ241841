package com.restaurante.saborgourmet.service;

import com.restaurante.saborgourmet.dto.PedidoDTO;
import com.restaurante.saborgourmet.dto.PedidoProductoDTO;
import com.restaurante.saborgourmet.model.*;
import com.restaurante.saborgourmet.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public Pedido getById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con id: " + id));
    }

    public List<Pedido> getByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Transactional
    public Pedido create(PedidoDTO pedidoDTO) {
        // Obtener el cliente
        Cliente cliente = clienteService.getById(pedidoDTO.getClienteId());

        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        pedido.setEstado(EstadoPedido.PENDIENTE);

        // Procesar los productos del pedido
        List<PedidoProducto> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (PedidoProductoDTO detalle : pedidoDTO.getProductos()) {
            Producto producto = productoService.getById(detalle.getProductoId());

            // Verificar stock
            if (producto.getStock() < detalle.getCantidad()) {
                throw new IllegalStateException(
                        "No hay suficiente stock para el producto: " + producto.getNombre() +
                                ". Stock disponible: " + producto.getStock());
            }

            // Crear detalle del pedido
            PedidoProducto pedidoProducto = new PedidoProducto();
            pedidoProducto.setPedido(pedido);
            pedidoProducto.setProducto(producto);
            pedidoProducto.setCantidad(detalle.getCantidad());
            pedidoProducto.setPrecioUnitario(producto.getPrecio());
            pedidoProducto.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())));

            detalles.add(pedidoProducto);
            total = total.add(pedidoProducto.getSubtotal());

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoService.update(producto.getId(), convertToDTO(producto));
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido updateEstado(Long id, EstadoPedido estado) {
        Pedido pedido = getById(id);
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    // Método auxiliar para convertir Producto a ProductoDTO
    private com.restaurante.saborgourmet.dto.ProductoDTO convertToDTO(Producto producto) {
        com.restaurante.saborgourmet.dto.ProductoDTO dto = new com.restaurante.saborgourmet.dto.ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setCategoria(producto.getCategoria());
        dto.setStock(producto.getStock());
        dto.setDisponible(producto.getDisponible());
        return dto;
    }
}