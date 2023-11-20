package com.farm.management.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_user_level")
public class UserLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 60)
    private String description;

    public UserLevel() {

    }

    public UserLevel(String description) {
        this.description = description;
    }
}
