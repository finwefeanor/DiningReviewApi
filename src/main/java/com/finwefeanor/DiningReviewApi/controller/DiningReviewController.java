package com.finwefeanor.DiningReviewApi.controller;

import com.finwefeanor.DiningReviewApi.datatransferobjects.DiningReviewDTO;
import com.finwefeanor.DiningReviewApi.datatransferobjects.DiningReviewResponseDTO;
import com.finwefeanor.DiningReviewApi.model.*;
import com.finwefeanor.DiningReviewApi.repository.DiningReviewRepository;
import com.finwefeanor.DiningReviewApi.repository.RestaurantRepository;
import com.finwefeanor.DiningReviewApi.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository,
                                  RestaurantRepository restaurantRepository,
                                  UserRepository userRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/pendingReviews")
    public List<DiningReviewResponseDTO> getPendingReviews() {
        List<DiningReview> pendingReviews = diningReviewRepository.findByStatus(ReviewStatus.PENDING);
        return pendingReviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DiningReviewResponseDTO convertToDTO(DiningReview review) {
        DiningReviewResponseDTO dto = new DiningReviewResponseDTO();
        dto.setId(review.getId());
        dto.setRestaurantName(review.getRestaurant().getRestaurantName());
        dto.setUserName(review.getUser().getDisplayName());
        dto.setPeanutScore(review.getPeanutScore());
        dto.setEggScore(review.getEggScore());
        dto.setDairyScore(review.getDairyScore());
        dto.setCommentary(review.getCommentary());
        return dto;
    }

    @PutMapping("/testPut")
    public ResponseEntity<String> testPut() {
        return ResponseEntity.ok("PUT request successful!");
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
    public ResponseEntity<String> createNewReview(@RequestBody DiningReviewDTO reviewDTO) {
        DiningReview review = new DiningReview();

        Optional<Restaurant> restaurantOpt = restaurantRepository.findByRestaurantName(reviewDTO.getRestaurantName());
        Optional<User> userOpt = userRepository.findByDisplayName(reviewDTO.getDisplayName());

        // If either restaurant or user isn't found, return an error.
        if (!restaurantOpt.isPresent() || !userOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Restaurant or User not found!");
        }

        Restaurant restaurant = restaurantOpt.get();
        User user = userOpt.get();

        review.setRestaurant(restaurant);
        review.setUser(user);
        review.setPeanutScore(reviewDTO.getPeanutScore());
        review.setEggScore(reviewDTO.getEggScore());
        review.setDairyScore(reviewDTO.getDairyScore());
        review.setCommentary(reviewDTO.getCommentary());

        diningReviewRepository.save(review);

        return ResponseEntity.ok("New review successfully added!");
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
