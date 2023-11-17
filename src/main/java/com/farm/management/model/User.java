package com.farm.management.model;

import com.farm.management.model.audit.DateAudit;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tb_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 40)
    private String name;

    private Double doc;

    private String address;
    private String city;
    private String phone;

    private Long zipcode;
    private Long created_by;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_level",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_level"))
    private Set<Level> roles = new HashSet<>();

    public User() {

    }

    public User(String name, String email, String password) {
        this.name = name;
        this.username = email;
        this.email = email;
        this.password = password;
    }
}