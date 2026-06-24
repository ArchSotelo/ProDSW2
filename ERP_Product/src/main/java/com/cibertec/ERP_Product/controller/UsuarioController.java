package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Usuario;
import com.cibertec.ERP_Product.repository.UsuarioRepository;
import com.cibertec.ERP_Product.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Listar Usuario
    @GetMapping
    public List<Usuario> listarUsuarios(){ return usuarioService.listarUsuario();}

    //Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable("id") Integer id){
        return usuarioService.buscarUsuarioPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Usuario no encontrado")));
    }

    //Insertar usuario
    @PostMapping
    public ResponseEntity<ApiResponse> guardarUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    //Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        usuario.setIdUsuario(id);
        return usuarioService.actualizarUsuario(usuario);
    }

    //Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarUsuarioPorId(@PathVariable Integer id){
        return usuarioService.eliminarUsuario(id);
    }
}
