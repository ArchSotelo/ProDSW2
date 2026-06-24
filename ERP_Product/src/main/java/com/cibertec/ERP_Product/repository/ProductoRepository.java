package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    boolean existsByCodigoProducto(String codigoProducto);
}
