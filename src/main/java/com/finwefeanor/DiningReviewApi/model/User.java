package com.finwefeanor.DiningReviewApi.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String displayName;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String zipCode;

    @OneToMany(mappedBy = "user")//For our example, a User can write many reviews,
    // so you would use this on the User side to describe the relationship with DiningReview.
    //The mappedBy attribute in @OneToMany tells JPA which field on the DiningReview entity
    // is managing the relationship (in this case, the submitter field).
    private List<DiningReview> reviews;

    boolean hasPeanutAllergy;
    boolean hasEggAllergy;
    boolean hasDairyAllergy;

}
