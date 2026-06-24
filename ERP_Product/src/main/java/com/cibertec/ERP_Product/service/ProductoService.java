package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Producto;
import com.cibertec.ERP_Product.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarProductoPorId(Integer id) {return productoRepository.findById(id);}

    public ResponseEntity<ApiResponse> guardarProducto(Producto producto) {
        if(productoRepository.existsByCodigoProducto(producto.getCodigoProducto())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El codigo de producto ya existe"));
        }

        productoRepository.save(producto);
        return ResponseEntity.ok().body(new ApiResponse(true, "Producto registado correcamente"));
    }

    public ResponseEntity<ApiResponse> actualizarProducto(Producto producto) {
        if(!productoRepository.existsById(producto.getIdProducto())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El producto no existe"));
        }

        productoRepository.save(producto);
        return ResponseEntity.ok().body(new ApiResponse(true, "Producto actualizado correcamente"));
    }


    public ResponseEntity<ApiResponse> eliminarProducto(Integer id) {
        if(!productoRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El producto no existe"));
        }

        productoRepository.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Producto eliminado correcamente"));
    }
}
