package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Almacen;
import com.cibertec.ERP_Product.model.Producto;
import com.cibertec.ERP_Product.model.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaldoRepository extends JpaRepository<Saldo, Integer> {
    Optional<Saldo> findByProductoAndAlmacen(Producto producto, Almacen almacen);
}
