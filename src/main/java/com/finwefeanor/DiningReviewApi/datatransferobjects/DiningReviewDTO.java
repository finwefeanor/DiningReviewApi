package com.finwefeanor.DiningReviewApi.datatransferobjects;

import lombok.Data;

@Data
public class DiningReviewDTO {

    private Long id;
    private String restaurantName; // Just the restaurant name, not the entire Restaurant object.
    private String displayName; // Just the user's name, not the entire User object.
    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;
    private String commentary;

    // No need for getters, setters, or default constructors - Lombok's @Data annotation takes care of them.
}
