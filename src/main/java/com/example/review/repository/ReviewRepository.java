package com.example.review.repository;

import com.example.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByProductId(Long productId);

    List<Review> findByProductIdOrderByCreatedAtDesc(Long productId);

    Optional<Review> findByUserIdAndProductId(Long userId,Long productId);
}
