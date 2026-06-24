package com.cibertec.ERP_Product.controller;


import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Cliente;
import com.cibertec.ERP_Product.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    //Listar Clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    //Buscar Cliente
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable("id") Integer id) {
        return clienteService.buscarClientePorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Cliente no encontrado")));
    }

    //Insertar Cliente
    @PostMapping
    public ResponseEntity<ApiResponse> guardarCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    //Actualizar Cliente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
        cliente.setIdCliente(id);
        return clienteService.actualizarCliente(cliente);
    }

    //Eliminar Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarCliente(@PathVariable("id") Integer id) {
        return clienteService.eliminarCliente(id);
    }
}
