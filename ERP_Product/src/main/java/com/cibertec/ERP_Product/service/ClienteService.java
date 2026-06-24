package com.cibertec.ERP_Product.service;


import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Cliente;
import com.cibertec.ERP_Product.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){ return clienteRepository.findAll(); }

    public Optional<Cliente> buscarClientePorId(Integer id){return clienteRepository.findById(id); }

    public ResponseEntity<ApiResponse> guardarCliente(Cliente cliente){
        if(clienteRepository.existsByCodigoCliente(cliente.getCodigoCliente())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El codigo de cliente ya existe"));
        }

        clienteRepository.save(cliente);
        return ResponseEntity.ok().body(new ApiResponse(true, "Cliente registrado correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarCliente(Cliente cliente){
        if(!clienteRepository.existsById(cliente.getIdCliente())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El cliente no existe"));
        }
        clienteRepository.save(cliente);
        return ResponseEntity.ok().body(new ApiResponse(true, "Cliente actualizado correctamente"));
    }

    public ResponseEntity<ApiResponse> eliminarCliente(Integer id){
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El cliente no existe"));
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Cliente eliminado correctamente"));
    }




}
