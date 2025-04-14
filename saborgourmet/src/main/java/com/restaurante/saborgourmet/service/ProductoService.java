package com.restaurante.saborgourmet.service;

import com.restaurante.saborgourmet.dto.ProductoDTO;
import com.restaurante.saborgourmet.model.Producto;
import com.restaurante.saborgourmet.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
    }

    @Transactional
    public Producto create(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        BeanUtils.copyProperties(productoDTO, producto);
        return productoRepository.save(producto);
    }

    @Transactional
    public Producto update(Long id, ProductoDTO productoDTO) {
        Producto producto = getById(id);
        BeanUtils.copyProperties(productoDTO, producto, "id");
        return productoRepository.save(producto);
    }

    @Transactional
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}