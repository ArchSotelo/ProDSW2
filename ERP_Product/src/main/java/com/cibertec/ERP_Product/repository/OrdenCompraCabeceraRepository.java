package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.OrdenCompraCabecera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenCompraCabeceraRepository extends JpaRepository<OrdenCompraCabecera, Integer> {
    List<OrdenCompraCabecera> findByEstado(String estado);
}
