package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    boolean existsByCodigoCategoria(String codigoCategoria);

}
