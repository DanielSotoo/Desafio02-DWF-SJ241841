package com.restaurante.saborgourmet.service;

import com.restaurante.saborgourmet.dto.ClienteDTO;
import com.restaurante.saborgourmet.model.Cliente;
import com.restaurante.saborgourmet.repository.ClienteRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Transactional
    public Cliente create(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new EntityExistsException("Ya existe un cliente con el email: " + clienteDTO.getEmail());
        }

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.setFechaRegistro(LocalDateTime.now());
        if (cliente.getActivo() == null) {
            cliente.setActivo(true);
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente update(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = getById(id);

        // Verificar que el email no esté en uso por otro cliente
        if (!cliente.getEmail().equals(clienteDTO.getEmail()) &&
                clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new EntityExistsException("Ya existe un cliente con el email: " + clienteDTO.getEmail());
        }

        BeanUtils.copyProperties(clienteDTO, cliente, "id", "fechaRegistro");
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}