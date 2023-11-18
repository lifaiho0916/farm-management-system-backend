package com.farm.management.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_farms_owner")
public class Farmsowner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_farm", nullable = false)
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;
}
