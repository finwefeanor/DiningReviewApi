package com.finwefeanor.DiningReviewApi.model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.finwefeanor.DiningReviewApi.serializers.DoubleSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String restaurantName;//restaurant name should be unique and not nullable

    @Pattern(regexp = "^[0-9]{5}$", message = "Invalid zip code format")
    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String restaurantAddress;

    @JsonSerialize(using = DoubleSerializer.class)
    private Double averagePeanutScore;

    @JsonSerialize(using = DoubleSerializer.class)
    private Double averageEggScore;

    @JsonSerialize(using = DoubleSerializer.class)
    private Double averageDairyScore;

    @JsonSerialize(using = DoubleSerializer.class)
    private Double averageOverallScore;

}
