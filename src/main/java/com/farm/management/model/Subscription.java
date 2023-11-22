package com.farm.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;
    
    private String subscription_id;
    private String customer_id;
    private String subscriptionPlan;

    public Subscription(User user, String subscription_id, String customer_id, String subscripitonPlan) {
        this.user = user;
        this.subscription_id = subscription_id;
        this.customer_id = customer_id;
        this.subscriptionPlan = subscripitonPlan;
    }
}
