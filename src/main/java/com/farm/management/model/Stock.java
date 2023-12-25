package com.farm.management.model;

import java.time.Instant;

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
@Table(name = "tb_stock_movement")
public class Stock {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_products")
    private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit")
    private Unit unit;
	
	private String types;
	private Double quantity_moved;
    private Instant date;

}
