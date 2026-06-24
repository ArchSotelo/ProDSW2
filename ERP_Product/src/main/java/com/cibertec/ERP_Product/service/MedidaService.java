package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Medida;
import com.cibertec.ERP_Product.repository.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedidaService {

    @Autowired
    private MedidaRepository medidaRepository;

    public List<Medida> listarMedidas() {return medidaRepository.findAll();}

    public Optional<Medida> buscarMedidaPorId(Integer id) {return medidaRepository.findById(id);}

    public ResponseEntity<ApiResponse> guardarMedida(Medida medida) {

        if(medidaRepository.existsByCodigoMedida(medida.getCodigoMedida())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Medida ya existe"));
        }

        medidaRepository.save(medida);
        return ResponseEntity.ok().body(new ApiResponse(true, "Medida registrada correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarMedida(Medida medida) {

        if(!medidaRepository.existsByCodigoMedida(medida.getCodigoMedida())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Medida no existe"));
        }

        medidaRepository.save(medida);
        return ResponseEntity.ok().body(new ApiResponse(true, "Medida actualizada correctamente"));
    }

    public ResponseEntity<ApiResponse> eliminarMedida(Integer id) {

        if (!medidaRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Medida no existe"));
        }

        medidaRepository.deleteById(id);

        return ResponseEntity.ok(new ApiResponse(true, "Medida eliminada correctamente"));
    }
}
