package com.cibertec.ERP_Product.controller;


import com.cibertec.ERP_Product.model.ApiResponse;
import com.cibertec.ERP_Product.model.Medida;
import com.cibertec.ERP_Product.service.MedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/medida")
@CrossOrigin(origins = "http://localhost:4200/")
public class MedidacController {

    @Autowired
    private MedidaService medidaService;

    //Listar medida
    @GetMapping
    public List<Medida> listaMedidas() {return medidaService.listarMedidas();}

    //Buscar medida por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMedidaPorId(@PathVariable("id") Integer id) {
        return medidaService.buscarMedidaPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Almacen no encontrado")));
    }

    //Insertar Medida
    @PostMapping
    public ResponseEntity<ApiResponse> guardarMedida(@RequestBody Medida medida) {
        return medidaService.guardarMedida(medida);
    }

    //Actualizar Medida
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarMedida(@PathVariable("id") Integer id, @RequestBody Medida medida) {
        medida.setIdMedida(id);
        return medidaService.actualizarMedida(medida);
    }

    //Eliminar Medida
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarMedida(@PathVariable("id") Integer id) {
        return medidaService.eliminarMedida(id);
    }
}
