package com.farm.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user_permission")
public class Userfarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_farm", nullable = false)
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;

    private String description;

    public Userfarm(Farm farm, User user, String description) {
        this.farm = farm;
        this.user = user;
        this.description = description;
    }
}
