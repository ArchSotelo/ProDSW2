package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.Auditoria;
import com.cibertec.ERP_Product.model.Usuario;
import com.cibertec.ERP_Product.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public void registrarAccion(Usuario usuario, String accion, String tabla) {
        Auditoria auditoria = new Auditoria();
        auditoria.setUsuario(usuario);
        auditoria.setAccion(accion);
        auditoria.setTablaAfectada(tabla);
        auditoria.setFecha(java.time.LocalDateTime.now());
        auditoriaRepository.save(auditoria);
    }

}
