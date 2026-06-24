package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.DTO.MovimientoRequest;
import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.MovimientoCabecera;
import com.cibertec.ERP_Product.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimiento")
@CrossOrigin(origins = "http://localhost:4200/")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<MovimientoCabecera> listaMovimientoCabecera() {return movimientoService.listaMovimientoCabecera();}

    @PostMapping
    public ResponseEntity<ApiResponse> registrarMovimiento(@RequestBody MovimientoRequest request) {
        movimientoService.registrarMovimiento(request);
        return ResponseEntity.ok(new ApiResponse(true, "Movimiento registrado y saldos actualizados"));
    }
}
