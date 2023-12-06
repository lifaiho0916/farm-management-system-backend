package com.farm.management.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_plot")
public class Plot {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_farm")
    private Farm farm;
	
	private String name;
	private String description;
	private Long area;
	private String type;
	
	public Plot(Farm farm, String name, String description, Long area, String type) {
		this.farm = farm;
		this.name = name;
		this.description = description;
		this.area = area;
		this.type = type;
	}
}
