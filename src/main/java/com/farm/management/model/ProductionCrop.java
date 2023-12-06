package com.farm.management.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_production_crop")
public class ProductionCrop {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_farm")
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crop")
    private Crop crop;
    
    private String quantity;

    public ProductionCrop(Farm farm, Unit unit, Crop crop, String quantity) {
        this.farm = farm;
        this.unit = unit;
        this.crop = crop;
        this.quantity = quantity;
    }
}
