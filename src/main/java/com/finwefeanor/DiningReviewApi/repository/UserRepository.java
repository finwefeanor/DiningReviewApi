package com.finwefeanor.DiningReviewApi.repository;

import com.finwefeanor.DiningReviewApi.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByDisplayName(String displayName);
    User findById(long id);
}
