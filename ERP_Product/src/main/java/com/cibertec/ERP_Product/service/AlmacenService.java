package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.Almacen;
import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Almacen> listarAlmacenes() { return almacenRepository.findAll(); }

    public Optional<Almacen> buscarAlmacenPorId(Integer id) {return almacenRepository.findById(id); }

    public ResponseEntity<ApiResponse> guardarAlmacen(Almacen almacen) {

        if(almacenRepository.existsByCodigoAlmacen(almacen.getCodigoAlmacen())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El codigo de almacen ya existe"));
        }

        almacenRepository.save(almacen);
        return ResponseEntity.ok().body(new ApiResponse(true, "Almacen registrado correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarAlmacen(Almacen almacen) {

        if(!almacenRepository.existsById(almacen.getIdAlmacen())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El almacen no existe"));
        }

        almacenRepository.save(almacen);
        return ResponseEntity.ok().body(new ApiResponse(true, "Almacen actualizado correctamente"));
    }

    public ResponseEntity<ApiResponse> eliminarAlmacen(Integer id) {
        if(!almacenRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El almacen no existe"));
        }

        almacenRepository.deleteById(id);

        return ResponseEntity.ok(new ApiResponse(true, "Almacen eliminado correctamente"));
    }


}
