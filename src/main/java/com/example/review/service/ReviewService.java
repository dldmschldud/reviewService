package com.example.review.service;

import com.example.review.domain.Product;
import com.example.review.domain.Review;
import com.example.review.dto.ReviewDto;
import com.example.review.dto.ReviewRequestDto;
import com.example.review.dto.ReviewResponseDto;
import com.example.review.repository.ProductRepository;
import com.example.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewResponseDto getReviews(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품없음"));
        List<Review> reviews = reviewRepository.findByProductIdOrderByCreatedAtDesc(id);
        List<ReviewDto> reviewDto = reviews.stream().map(ReviewDto::new).collect(Collectors.toList());
        int totalCount = reviews.size();
        float score = product.getScore();
        int cursor = totalCount > 0 ? reviews.get(reviews.size() - 1).getId().intValue() : 0;
        return new ReviewResponseDto(totalCount,score,cursor,reviewDto);
    }

    public void createReview(Long id, ReviewRequestDto reviewRequestDto) {//userid, score, content

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품없음"));

        Optional<Review> review = reviewRepository.findByUserIdAndProductId(reviewRequestDto.getUserId(),id);
        if (review.isPresent()){
            throw new IllegalStateException("한 상품에 하나의 리뷰만 작성 가능합니다");
        }

        product.change(reviewRequestDto.getScore());
        Review resultReview = new Review(product,reviewRequestDto);
        reviewRepository.save(resultReview);
    }

}
