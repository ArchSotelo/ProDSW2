package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByCodigoCliente(String codigoCliente);
}
