package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

    boolean existsByCodigoAlmacen(String codigoAlmacen);
}
