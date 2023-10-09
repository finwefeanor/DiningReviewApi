package com.finwefeanor.DiningReviewApi.controller;

import com.finwefeanor.DiningReviewApi.model.DiningReview;
import com.finwefeanor.DiningReviewApi.model.Restaurant;
import com.finwefeanor.DiningReviewApi.model.ReviewStatus;
import com.finwefeanor.DiningReviewApi.model.User;
import com.finwefeanor.DiningReviewApi.repository.DiningReviewRepository;
import com.finwefeanor.DiningReviewApi.repository.RestaurantRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            return optionalRestaurant.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found");
        }
    }
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /* TODO
    As an application experience, I want to fetch restaurants that match a given zip code and
    that also have at least one user-submitted score for a given allergy.
    I want to see them sorted in descending order.
    *   */

    @GetMapping("/byZipCodeAndAllergen")
    public List<Restaurant> getRestaurantsWithZipCodeAndAllergen(
            @RequestParam String zipCode,
            @RequestParam String allergen) {

        if ("peanut".equalsIgnoreCase(allergen)) {
            return restaurantRepository.findByZipCodeAndAveragePeanutScoreNotNullOrderByAveragePeanutScoreDesc(zipCode);
        } else if ("egg".equalsIgnoreCase(allergen)) {
            return restaurantRepository.findByZipCodeAndAverageEggScoreNotNullOrderByAverageEggScoreDesc(zipCode);
        } else if ("dairy".equalsIgnoreCase(allergen)) {
            return restaurantRepository.findByZipCodeAndAverageDairyScoreNotNullOrderByAverageDairyScoreDesc(zipCode);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid allergen type");
        }
    }

    @PostMapping //no need addNewRestaurant endpoint
    public ResponseEntity<String> createNewRestaurant(@RequestBody Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.
                findByRestaurantNameAndZipCode(restaurant.getRestaurantName(), restaurant.getZipCode());
        if (optionalRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant already exists in the specified zip code!");
        }
        restaurantRepository.save(restaurant);
        return new ResponseEntity<>("New review successfully added!", HttpStatus.CREATED) ;
    }
    
}
