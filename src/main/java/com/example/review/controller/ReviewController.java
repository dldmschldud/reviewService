package com.example.review.controller;

import com.example.review.dto.ReviewRequestDto;
import com.example.review.dto.ReviewResponseDto;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Log4j2
@RequestMapping("/products")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}/reviews")
    public ReviewResponseDto getReviews(@PathVariable("productId") Long id){
        ReviewResponseDto reviewResponseDto = reviewService.getReviews(id);

        return reviewResponseDto;
    }

    @PostMapping("/{productId}/reviews")
    public ResponseEntity<String> createReview(@PathVariable("productId") Long id, @RequestBody ReviewRequestDto reviewRequestDto) {
        try {//이부분 어떻게 처리하는게 좋을지 고민해보기
            reviewService.createReview(id, reviewRequestDto);
            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

}
