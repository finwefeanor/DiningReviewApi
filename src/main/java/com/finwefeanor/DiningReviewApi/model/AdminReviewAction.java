package com.finwefeanor.DiningReviewApi.model;
import lombok.Data;

@Data
public class AdminReviewAction {
    //No need to define jpa annotations here because this class won't be storing inside a db,
    //it is not persisting. it will be handled in controller logic
    private Long reviewId;
    private boolean reviewAccepted;
}
