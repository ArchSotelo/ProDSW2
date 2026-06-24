package com.cibertec.ERP_Product.controller;

import com.cibertec.ERP_Product.model.Almacen;
import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Documento;
import com.cibertec.ERP_Product.repository.DocumentoRepository;
import com.cibertec.ERP_Product.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documento")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    //Listar Documentos
    @GetMapping
    public List<Documento> listarDocumentos(){return documentoService.listarDocumentos();}

    //Buscar Documentos por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDocumento(@PathVariable("id") Integer id){
        return documentoService.buscarDocumentoPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false,"Documento no encontrado")));
    }

    //Insertar Documento
    @PostMapping
    public ResponseEntity<ApiResponse> crearDocumento(@RequestBody Documento documento){
        return documentoService.guardarDocumento(documento);
    }

    //Actualizar Documento
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarDocumento(@PathVariable("id") Integer id, @RequestBody Documento documento){
        documento.setIdDocumento(id);
        return documentoService.actualizarDocumento(documento);
    }

    //Eliminar Documento
    public ResponseEntity<ApiResponse> eliminarDocumento(@PathVariable("id") Integer id){
        return documentoService.eliminarDocumento(id);
    }
}
