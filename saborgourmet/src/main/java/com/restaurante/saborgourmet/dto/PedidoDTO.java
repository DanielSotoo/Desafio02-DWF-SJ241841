package com.restaurante.saborgourmet.dto;

import com.restaurante.saborgourmet.model.EstadoPedido;
import com.restaurante.saborgourmet.model.MetodoPago;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long id;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    private EstadoPedido estado;

    @NotEmpty(message = "Debe incluir al menos un producto")
    private List<PedidoProductoDTO> productos;
}