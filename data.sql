-- Insertar datos en la tabla CLIENTE
INSERT INTO CLIENTE (ID, NOMBRE, EMAIL, TELEFONO, DIRECCION, ACTIVO, FECHA_REGISTRO) VALUES
(1, 'María López', 'maria.lopez@email.com', '555-1234', 'Av. Principal 123', TRUE, '2024-01-10'),
(2, 'Carlos Rodríguez', 'carlos.rodriguez@email.com', '555-5678', 'Calle Secundaria 456', TRUE, '2024-01-15'),
(3, 'Ana García', 'ana.garcia@email.com', '555-9012', 'Plaza Central 789', TRUE, '2024-02-01'),
(4, 'Juan Martínez', 'juan.martinez@email.com', '555-3456', 'Paseo del Parque 234', TRUE, '2024-02-15'),
(5, 'Laura Sánchez', 'laura.sanchez@email.com', '555-7890', 'Callejón del Sol 567', FALSE, '2024-03-01');

-- Insertar datos en la tabla PRODUCTO
INSERT INTO PRODUCTO (ID, NOMBRE, DESCRIPCION, PRECIO, CATEGORIA, STOCK, DISPONIBLE) VALUES
(1, 'Paella Valenciana', 'Auténtica paella con mariscos frescos y azafrán', 24.99, 'PLATO_PRINCIPAL', 50, TRUE),
(2, 'Gazpacho', 'Sopa fría de tomates y pimientos', 9.99, 'ENTRANTE', 75, TRUE),
(3, 'Crema Catalana', 'Postre tradicional con azúcar caramelizado', 7.99, 'POSTRE', 40, TRUE),
(4, 'Sangría', 'Bebida refrescante de vino tinto con frutas', 14.99, 'BEBIDA', 60, TRUE),
(5, 'Tortilla Española', 'Tortilla de patatas con cebolla caramelizada', 12.99, 'ENTRANTE', 45, TRUE),
(6, 'Pulpo a la Gallega', 'Pulpo cocido con pimentón y aceite de oliva', 22.99, 'PLATO_PRINCIPAL', 30, TRUE),
(7, 'Tarta de Santiago', 'Tarta de almendras tradicional gallega', 8.99, 'POSTRE', 25, TRUE),
(8, 'Jamón Ibérico', 'Ración de jamón ibérico de bellota', 32.99, 'ENTRANTE', 15, TRUE),
(9, 'Vino Rioja Reserva', 'Botella de vino tinto reserva', 29.99, 'BEBIDA', 50, TRUE),
(10, 'Croquetas de Jamón', 'Croquetas caseras de jamón ibérico', 10.99, 'ENTRANTE', 0, FALSE);

-- Insertar datos en la tabla PEDIDO
INSERT INTO PEDIDO (ID, CLIENTE_ID, ESTADO, FECHA_PEDIDO, METODO_PAGO, TOTAL) VALUES
(1, 1, 'ENTREGADO', '2024-03-15 19:30:00', 'TARJETA', 67.97),
(2, 3, 'PREPARANDO', '2024-03-28 20:15:00', 'EFECTIVO', 47.98),
(3, 2, 'ENTREGADO', '2024-03-27 13:45:00', 'TARJETA', 89.95),
(4, 1, 'CANCELADO', '2024-04-01 19:00:00', 'TARJETA', 32.99),
(5, 4, 'PAGADO', '2024-04-05 21:30:00', 'TRANSFERENCIA', 92.97),
(6, 5, 'EN_CAMINO', '2024-04-10 20:00:00', 'EFECTIVO', 58.97);

-- Insertar datos en la tabla PEDIDO_PRODUCTO
INSERT INTO PEDIDO_PRODUCTO (ID, PEDIDO_ID, PRODUCTO_ID, CANTIDAD, PRECIO_UNITARIO, SUBTOTAL) VALUES
(1, 1, 1, 1, 24.99, 24.99),
(2, 1, 5, 1, 12.99, 12.99),
(3, 1, 3, 2, 7.99, 15.98),
(4, 1, 4, 1, 14.99, 14.99),
(5, 2, 2, 2, 9.99, 19.98),
(6, 2, 6, 1, 22.99, 22.99),
(7, 2, 7, 1, 8.99, 8.99),
(8, 3, 8, 1, 32.99, 32.99),
(9, 3, 9, 1, 29.99, 29.99),
(10, 3, 5, 2, 12.99, 25.98),
(11, 4, 8, 1, 32.99, 32.99),
(12, 5, 1, 2, 24.99, 49.98),
(13, 5, 4, 2, 14.99, 29.98),
(14, 5, 3, 1, 7.99, 7.99),
(15, 5, 7, 1, 8.99, 8.99),
(16, 6, 6, 2, 22.99, 45.98),
(17, 6, 2, 1, 9.99, 9.99),
(18, 6, 3, 1, 7.99, 7.99);