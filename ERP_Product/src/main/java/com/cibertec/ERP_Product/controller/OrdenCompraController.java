package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.DTO.OrdenCompraRequest;
import com.cibertec.ERP_Product.model.OrdenCompraCabecera;
import com.cibertec.ERP_Product.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes-compras")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    //Registrar nueva orden de compra
    @PostMapping
    public ResponseEntity<OrdenCompraCabecera> registrarOrdenCompra(@RequestBody OrdenCompraRequest request) {
        OrdenCompraCabecera cabecera = ordenCompraService.registrarOrdenCompra(request);
        return ResponseEntity.ok(cabecera);
    }

    //Aprobar orden de compra
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<OrdenCompraCabecera> aprobarOrden(@PathVariable("id") Integer id) {
        OrdenCompraCabecera cabecera = ordenCompraService.aprobarOrden(id);
        return ResponseEntity.ok(cabecera);
    }

    //Rechazar orden de compra
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<OrdenCompraCabecera> rechazarOrden(@PathVariable("id") Integer id, @RequestParam String motivo) {
        OrdenCompraCabecera cabecera = ordenCompraService.rechazarOrden(id, motivo);
        return ResponseEntity.ok(cabecera);
    }

    //Cerrar ordenes de compra
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<OrdenCompraCabecera> cerrarOrden(@PathVariable("id") Integer id) {
        OrdenCompraCabecera cabecera = ordenCompraService.cerrarOrden(id);
        return ResponseEntity.ok(cabecera);
    }

    // Listar órdenes por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenCompraCabecera>> listarPorEstado(@PathVariable String estado) {
        List<OrdenCompraCabecera> ordenes = ordenCompraService.listarPorEstado(estado);
        return ResponseEntity.ok(ordenes);
    }



}
