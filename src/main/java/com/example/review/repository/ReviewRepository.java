package com.example.review.repository;

import com.example.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByProductId(Long productId);

    Page<Review> findByProductIdOrderByCreatedAtDesc(Long productId, Pageable pageable);
    Page<Review> findByProductIdAndIdLessThanOrderByCreatedAtDesc(Long productId, Long cursor, Pageable pageable);
    Optional<Review> findByUserIdAndProductId(Long userId,Long productId);
}
