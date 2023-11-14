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
    private Long id_user_level;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private LevelName description;

    public UserLevel() {

    }

    public UserLevel(LevelName description) {
        this.description = description;
    }

}
