package com.finwefeanor.DiningReviewApi.controller;

import com.finwefeanor.DiningReviewApi.model.*;
import com.finwefeanor.DiningReviewApi.repository.DiningReviewRepository;
import com.finwefeanor.DiningReviewApi.repository.RestaurantRepository;
import com.finwefeanor.DiningReviewApi.repository.UserRepository;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository,
                                  RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/pendingReviews")
    public List<DiningReview> getPendingReviews(){

        return diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    @PutMapping("/admin/{id}/update")
    public ResponseEntity<DiningReview> updateReview(@PathVariable Long id,
                           @RequestBody AdminReviewAction action) {
        Optional<DiningReview> reviewToOptionalToUpdate = diningReviewRepository.findById(id);
        if (!reviewToOptionalToUpdate.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Review not Found");
        }
        DiningReview existingReview = reviewToOptionalToUpdate.get();

        if(action.isReviewAccepted()){
            existingReview.setStatus(ReviewStatus.ACCEPTED);
        }
        else{
            existingReview.setStatus(ReviewStatus.REJECTED);
        }
        DiningReview updatedReview = diningReviewRepository.save(existingReview);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
    @PostMapping("/newReviews")
    public String createNewReview(@RequestBody DiningReview diningReview) {
        diningReviewRepository.save(diningReview);
        return "New review successfully added!";
    }
    @GetMapping("/restaurant/{restaurantId}/approvedReviews")
    public List<DiningReview> getApprovedReviewsForRestaurant(@PathVariable Long restaurantId){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (!optionalRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found");
        }
        Restaurant restaurant = optionalRestaurant.get();
        return diningReviewRepository.findByRestaurantAndStatus(restaurant, ReviewStatus.ACCEPTED);
    }

}
