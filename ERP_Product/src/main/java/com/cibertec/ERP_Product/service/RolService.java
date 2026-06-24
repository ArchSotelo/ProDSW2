package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Rol;
import com.cibertec.ERP_Product.repository.RolRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {


    @Autowired
    private RolRespository rolRespository;

    public List<Rol> listarRoles(){return rolRespository.findAll();}

    public Optional<Rol> buscarRolPorId(Integer id){return  rolRespository.findById(id);}


    public ResponseEntity<ApiResponse> guardarRol(Rol rol){
        if (rolRespository.existsByCodigoRol(rol.getCodigoRol())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El codigo rol no existe"));
        }

        rolRespository.save(rol);
        return ResponseEntity.ok().body(new ApiResponse(true, "Rol guardado"));
    }

    public ResponseEntity<ApiResponse> actualizarRol(Rol rol){
        if(!rolRespository.existsByCodigoRol(rol.getCodigoRol())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El codigo rol no existe"));
        }
        rolRespository.save(rol);
        return ResponseEntity.ok().body(new ApiResponse(true, "Rol actualizado correctamente"));
    }


    public ResponseEntity<ApiResponse> eliminarRol(Integer id){
        if(!rolRespository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El rol no existe"));
        }

        rolRespository.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Rol eliminado"));
    }
}
