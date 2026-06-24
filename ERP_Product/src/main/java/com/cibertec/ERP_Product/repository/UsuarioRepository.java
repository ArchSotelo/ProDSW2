package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol")
    List<Usuario> findAllWithRol();

    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.idUsuario = :id")
    Optional<Usuario> findByIdWithRol(@Param("id") Integer id);

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}
