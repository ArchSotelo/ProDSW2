package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    boolean existsByCodigoProveedor(String codigoProveedor);
}
