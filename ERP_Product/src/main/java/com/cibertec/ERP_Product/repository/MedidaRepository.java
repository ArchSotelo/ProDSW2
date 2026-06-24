package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Medida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedidaRepository extends JpaRepository<Medida, Integer> {

    boolean existsByCodigoMedida(String codigoMedida);
}
