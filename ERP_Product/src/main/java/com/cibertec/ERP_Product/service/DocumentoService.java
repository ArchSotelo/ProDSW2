package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Documento;
import com.cibertec.ERP_Product.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> listarDocumentos() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> buscarDocumentoPorId(Integer id) {
        return documentoRepository.findById(id);
    }

    public ResponseEntity<ApiResponse> guardarDocumento(Documento documento) {
        documentoRepository.save(documento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Documento creado correctamente"));
    }

    public ResponseEntity<ApiResponse> actualizarDocumento(Documento documento) {
        if(!documentoRepository.existsById(documento.getIdDocumento())){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Documento no encontrado"));
        }
        documentoRepository.save(documento);
        return ResponseEntity.ok(new ApiResponse(true, "Documento actualizado correctamente"));
    }

    public ResponseEntity<ApiResponse> eliminarDocumento(Integer id) {
        if (documentoRepository.existsById(id)) {
            documentoRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Documento eliminado correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, "Documento no encontrado"));
    }

}
