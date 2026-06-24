package com.cibertec.ERP_Product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_unidad_medida")
public class Medida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medida",  nullable = false)
    private Integer idMedida;

    @Column(name = "codigo_medida",  nullable = false,  unique = true, length = 10 )
    private String codigoMedida;

    @Column(name = "descripcion", length = 100 )
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
