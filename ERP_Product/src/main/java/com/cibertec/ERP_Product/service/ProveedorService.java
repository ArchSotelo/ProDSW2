package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Proveedor;
import com.cibertec.ERP_Product.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listarProveedores() {return proveedorRepository.findAll();}

    public Optional<Proveedor> buscarProveedorPorId(Integer id) {return proveedorRepository.findById(id);}

    public ResponseEntity<ApiResponse> guardarProveedor(Proveedor proveedor) {
        if(proveedorRepository.existsByCodigoProveedor(proveedor.getCodigoProveedor())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El codigo Proveedor ya existe"));
        }
        proveedorRepository.save(proveedor);
        return ResponseEntity.ok().body(new ApiResponse(true, "Proveedor registrado correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarProveedor(Proveedor proveedor) {
        if(!proveedorRepository.existsById(proveedor.getIdProveedor())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El Proveedor no existe"));
        }

        proveedorRepository.save(proveedor);
        return ResponseEntity.ok().body(new ApiResponse(true, "Proveedor actualizado correctamente"));
    }

    public ResponseEntity<ApiResponse> eliminarProveedor(Integer id) {
        if(!proveedorRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Proveedor no existe"));
        }

        proveedorRepository.deleteById(id);
        return  ResponseEntity.ok().body(new ApiResponse(true, "Proveedor eliminado"));
    }
}
