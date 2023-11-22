package com.farm.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_subscriptions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users", nullable = false)
    @JsonIgnore
    private User user;
    
    private String subscription_id;
    private String customer_id;

    public Subscriptions(User user, String subscription_id, String customer_id) {
        this.user = user;
        this.subscription_id = subscription_id;
        this.customer_id = customer_id;
    }
}
