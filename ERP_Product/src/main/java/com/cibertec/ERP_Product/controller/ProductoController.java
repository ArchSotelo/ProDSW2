package com.cibertec.ERP_Product.controller;


import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Producto;
import com.cibertec.ERP_Product.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //Listar producto
    @GetMapping
    public List<Producto> listarProductos(){return productoService.listarProductos();}

    //Buscar producto por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable("id") Integer id){
        return productoService.buscarProductoPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Producto no encontrado")));
    }

    //Insertar Producto
    @PostMapping
    public ResponseEntity<ApiResponse> crearProducto(@RequestBody Producto producto){
        return productoService.guardarProducto(producto);
    }

    //Actualizar Producto
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarProducto(@PathVariable("id") Integer id, @RequestBody Producto producto){
        producto.setIdProducto(id);
        return productoService.actualizarProducto(producto);
    }

    //Eliminar Producto
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarProducto(@PathVariable("id") Integer id){
        return productoService.eliminarProducto(id);
    }
}
