package com.finwefeanor.DiningReviewApi.datatransferobjects;
import lombok.Data;

@Data
public class DiningReviewResponseDTO {
    private Long id;
    private String restaurantName;
    private String userName;
    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;
    private String commentary;


}