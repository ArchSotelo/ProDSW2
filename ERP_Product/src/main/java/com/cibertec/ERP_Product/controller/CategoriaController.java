package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Categoria;
import com.cibertec.ERP_Product.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Listar categorías
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    // Buscar categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable("id") Integer id) {
        return categoriaService.buscarCategoriaPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Categoría no encontrada")));
    }



    // Insertar nueva categoría
    @PostMapping
    public ResponseEntity<ApiResponse> guardarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarCategoria(@PathVariable("id") Integer id,@RequestBody Categoria categoria) {
        categoria.setIdCategoria(id); // aseguramos que se actualice la correcta
        return categoriaService.actualizarCategoria(categoria);
    }

    // Eliminación lógica de categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarCategoria(@PathVariable("id") Integer id) {
        return categoriaService.borrarCategoria(id);
    }
}