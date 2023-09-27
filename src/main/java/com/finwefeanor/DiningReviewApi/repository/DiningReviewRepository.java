package com.finwefeanor.DiningReviewApi.repository;

import com.finwefeanor.DiningReviewApi.model.DiningReview;
import com.finwefeanor.DiningReviewApi.model.Restaurant;
import com.finwefeanor.DiningReviewApi.model.ReviewStatus;
import com.finwefeanor.DiningReviewApi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {

    DiningReview findById(long id);
    List<DiningReview> findByRestaurant(Restaurant restaurantId);
    List<DiningReview> findByStatus(ReviewStatus status);
    List<DiningReview> findByRestaurantAndStatus(Restaurant restaurant, ReviewStatus status);

}
