package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Categoria;
import com.cibertec.ERP_Product.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public ResponseEntity<ApiResponse> guardarCategoria(Categoria categoria) {
        // Validar que no se repita el código
        if (categoriaRepository.existsByCodigoCategoria(categoria.getCodigoCategoria())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El código de categoría ya existe"));
        }

        categoriaRepository.save(categoria);
        return ResponseEntity.ok(new ApiResponse(true, "Categoría registrada correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarCategoria(Categoria categoria) {
        if (!categoriaRepository.existsById(categoria.getIdCategoria())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "La categoría no existe"));
        }

        categoriaRepository.save(categoria);
        return ResponseEntity.ok(new ApiResponse(true, "Categoría actualizada correctamente"));
    }

    public ResponseEntity<ApiResponse> borrarCategoria(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "La categoría no existe"));
        }

        categoriaRepository.deleteById(id);

        return ResponseEntity.ok(new ApiResponse(true, "Categoría eliminada correctamente"));
    }

}
