package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Proveedor;
import com.cibertec.ERP_Product.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    //Listar Proveedor
    @GetMapping
    public List<Proveedor> listarProveedores() {return proveedorService.listarProveedores();}

    //Buscar Proveedor
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProveedorPorId(@PathVariable("id") Integer id) {
        return proveedorService.buscarProveedorPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Proveedor no encontrado")));
    }

    //Insertar Proveedor
    @PostMapping
    public ResponseEntity<ApiResponse> crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.guardarProveedor(proveedor);
    }

    //Actualizar Proveedor
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarProveedor(@PathVariable("id") Integer id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);
        return proveedorService.actualizarProveedor(proveedor);
    }

    //Eliminar Proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarProveedor(@PathVariable("id") Integer id) {
        return proveedorService.eliminarProveedor(id);
    }
}
