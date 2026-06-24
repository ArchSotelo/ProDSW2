package com.cibertec.ERP_Product.repository;

import com.cibertec.ERP_Product.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolRespository  extends JpaRepository<Rol, Integer> {

    boolean existsByCodigoRol(String codigoRol);

}
