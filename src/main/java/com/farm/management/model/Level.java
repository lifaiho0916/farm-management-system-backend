package com.farm.management.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private LevelName name;

    public Level() {

    }

    public Level(LevelName name) {
        this.name = name;
    }

}
