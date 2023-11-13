package com.farm.management.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  @Email
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  @Size(max = 20)
  private String firstname;

  @NotBlank
  @Size(max = 20)
  private String lastname;

  @Column(nullable = true)
  private String doc;

  @Column(nullable = true)
  private String address;

  @Column(nullable = true)
  private String city;

  @Column(nullable = true)
  private String state;

  @Column(nullable = true)
  private String phone;

  @Column(nullable = true)
  private String zipcode;

  @Range(min = 0, max = 100)
  private Integer created_by = 0;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String firstname, String lastname, String email, String password ) {
    this.username = email;
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }
}
