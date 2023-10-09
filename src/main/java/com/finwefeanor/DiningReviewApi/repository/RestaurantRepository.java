package com.finwefeanor.DiningReviewApi.repository;

import com.finwefeanor.DiningReviewApi.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    // 1. Check if a restaurant with the same name and zip code already exists
    Optional<Restaurant> findByRestaurantNameAndZipCode(String name, String zipCode);

    // 2. Fetching details by unique ID is already provided by CrudRepository's findById(Long id) method
    Restaurant findById(long id);

    // 3. Fetch restaurants matching a given zip code and that have at least one user-submitted score for a given allergy.
    // For this one, it's a bit more complex due to the requirement of having a user-submitted score for a given allergy.
    // This might require a custom query using @Query annotation in combination with the method,
    // but as a simple example (which assumes that a null score means no reviews):
    List<Restaurant> findByZipCodeAndAveragePeanutScoreNotNullOrderByAveragePeanutScoreDesc(String zipCode);
    List<Restaurant> findByZipCodeAndAverageEggScoreNotNullOrderByAverageEggScoreDesc(String zipCode);
    List<Restaurant> findByZipCodeAndAverageDairyScoreNotNullOrderByAverageDairyScoreDesc(String zipCode);

    Optional<Restaurant> findByRestaurantName(String restaurantName);
}
