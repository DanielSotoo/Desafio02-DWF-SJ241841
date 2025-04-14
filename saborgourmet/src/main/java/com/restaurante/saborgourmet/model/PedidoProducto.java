package com.restaurante.saborgourmet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pedido_producto")
public class PedidoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull
    @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a cero")
    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @NotNull
    @DecimalMin(value = "0.0", message = "El subtotal no puede ser negativo")
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
}