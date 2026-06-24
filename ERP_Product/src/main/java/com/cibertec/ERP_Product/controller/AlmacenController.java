package com.cibertec.ERP_Product.controller;


import com.cibertec.ERP_Product.model.Almacen;
import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almacen")
@CrossOrigin(origins = "http://localhost:4200/")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    //Listar Almacen
    @GetMapping
    public List<Almacen> listarAlmacenes() {return almacenService.listarAlmacenes();}

    //Buscar Almacen por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAlmacenPorId(@PathVariable("id") Integer id) {
        return almacenService.buscarAlmacenPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Almacen no encontrado")));
    }

    //Insertar Almacen
    @PostMapping
    public ResponseEntity<ApiResponse> guardarAlmacen(@RequestBody Almacen almacen) {
        return almacenService.guardarAlmacen(almacen);
    }

    //Actualizar Almacen
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarAlmacen(@PathVariable("id") Integer id, @RequestBody Almacen almacen) {
        almacen.setIdAlmacen(id);
        return almacenService.actualizarAlmacen(almacen);
    }

    //Eliminar Almacen
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarAlmacen(@PathVariable("id") Integer id) {
        return almacenService.eliminarAlmacen(id);
    }

}
