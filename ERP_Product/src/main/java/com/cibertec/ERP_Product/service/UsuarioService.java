package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Usuario;
import com.cibertec.ERP_Product.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuario() {return usuarioRepository.findAllWithRol();}

    public Optional<Usuario> buscarUsuarioPorId(Integer id){return usuarioRepository.findByIdWithRol(id);}

    public ResponseEntity<ApiResponse> guardarUsuario(Usuario usuario){
        if(usuarioRepository.existsByCorreo(usuario.getCorreo())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El correo ya existe."));
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(new ApiResponse(true, "Usuario guardado correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarUsuario(Usuario usuario){
        if (!usuarioRepository.existsById(usuario.getIdUsuario())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El Usuario no existe en el sistema."));
        }

        Usuario existente = usuarioRepository.findById(usuario.getIdUsuario()).orElseThrow();

        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            usuario.setPassword(existente.getPassword());
        }

        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(new ApiResponse(true, "Usuario Actualizado correctamente"));
    }

    public ResponseEntity<ApiResponse> eliminarUsuario(Integer id){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El Usuario no existe."));
        }

        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Usuario eliminado exitamente"));
    }
}
