package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.DTO.LoginRequest;
import com.cibertec.ERP_Product.DTO.LoginResponse;
import com.cibertec.ERP_Product.model.Usuario;
import com.cibertec.ERP_Product.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepo.findByCorreo(request.getCorreo()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if(!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }

        String token = jwtService.generateToken(usuario);

        return new LoginResponse(token, usuario.getRol().getNombreRol(), usuario.getNombres());
    }
}
