package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Rol;
import com.cibertec.ERP_Product.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    private RolService rolService;

    //Listar Rol
    @GetMapping
    public List<Rol> listarRols(){return rolService.listarRoles();}

    //Buscar Rol por ID

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRolPorId(@PathVariable("id") Integer id){
        return rolService.buscarRolPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Rol no encontrado")));
    }

    //Insertar Rol
    @PostMapping
    public ResponseEntity<ApiResponse> crearRol(@RequestBody Rol rol){
        return rolService.guardarRol(rol);
    }

    //Actualizar Rol
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarRol(@PathVariable("id") Integer id, @RequestBody Rol rol){
        rol.setIdRol(id);
        return rolService.actualizarRol(rol);
    }

    //Eliminar Rol
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarRol(@PathVariable("id") Integer id){
        return rolService.eliminarRol(id);
    }

}

