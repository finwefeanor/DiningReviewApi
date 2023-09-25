package com.finwefeanor.DiningReviewApi.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String restaurantName;//restaurant name should be unique and not nullable

    private String restaurantAddress;
    private Double averagePeanutScore;
    private Double averageEggScore;
    private Double averageDairyScore;
    private Double averageOverallScore;

}
