package com.finwefeanor.DiningReviewApi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;
//import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "app_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Display name cannot be null")
    @Size(min=3, max=20, message="Display name should have between 3 and 20 characters")
    @Column(nullable = false, unique = true)
    private String displayName;

    @NotNull(message = "City cannot be null")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "Country name cannot be null")
    @Column(nullable = false)
    private String country;

    @NotNull(message = "Zip code cannot be null")
    @Pattern(regexp = "^[0-9]{5}$", message = "Invalid zip code format")
    @Column(nullable = false)
    private String zipCode;

    @JsonIgnore
    @OneToMany(mappedBy = "user")//For our example, a User can write many reviews,
    // so you would use this on the User side to describe the relationship with DiningReview.
    //The mappedBy attribute in @OneToMany tells JPA which field on the DiningReview entity
    // is managing the relationship (in this case, the submitter field).
    private List<DiningReview> reviews;

    boolean hasPeanutAllergy;
    boolean hasEggAllergy;
    boolean hasDairyAllergy;
}
