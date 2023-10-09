package com.finwefeanor.DiningReviewApi.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne //This is used on the many side of a many-to-one relationship. For instance,
    // if you think about the relationship between DiningReview and User,
    // many reviews can be written by a single user.
    // So, the DiningReview class has a @ManyToOne relationship with the User class.
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;
    private String commentary;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING) // This ensures the enum value's name is used instead of ordinal value.
    private ReviewStatus status = ReviewStatus.PENDING;

}

